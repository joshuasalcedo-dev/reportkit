package io.joshuasalcedo.reportkit;

import freemarker.core.XHTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.xhtmlrenderer.pdf.ITextRenderer;
import io.joshuasalcedo.reportkit.chart.*;
import io.joshuasalcedo.reportkit.invoice.InvoiceDocument;
import io.joshuasalcedo.reportkit.report.CompanyProfile;
import io.joshuasalcedo.reportkit.report.DocumentStyle;
import io.joshuasalcedo.reportkit.report.Report;
import io.joshuasalcedo.reportkit.report.ReportSection;

public interface ReportExporter {

  PDF toPDF(Html html);

  Html toHTML(Report report);

  Html toHTML(Report report, ReportStyle style);

  /** An invoice or similar billing document as an HTML page. */
  Html toHTML(InvoiceDocument invoice, ReportStyle style);

  default Html toHTML(InvoiceDocument invoice) {
    return toHTML(invoice, ReportStyle.DEFAULT);
  }

  default PDF invoiceToPDF(InvoiceDocument invoice) {
    return toPDF(toHTML(invoice));
  }

  default PDF reportToPDF(Report report) {
    return toPDF(toHTML(report));
  }

  default PDF reportToPDF(Report report, ReportStyle style) {
    return toPDF(toHTML(report, style));
  }

  static ReportExporter createDefault() {
    return new DefaultReportExporter();
  }
}

class DefaultReportExporter implements ReportExporter {

  private static final String[] DEFAULT_COLORS = {
    "#3b82f6", "#22c55e", "#ef4444", "#f59e0b",
    "#8b5cf6", "#ec4899", "#14b8a6", "#f97316",
    "#06b6d4", "#6366f1"
  };

  private static final DateTimeFormatter CREATED_FMT =
      DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' hh:mm a").withZone(ZoneId.systemDefault());

  private static final DateTimeFormatter TP_FMT =
      DateTimeFormatter.ofPattern("MMM dd HH:mm").withZone(ZoneId.systemDefault());

  private final Configuration cfg;

  DefaultReportExporter() {
    cfg = new Configuration(Configuration.VERSION_2_3_34);
    cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "io/joshuasalcedo/reportkit/templates");
    cfg.setDefaultEncoding("UTF-8");
    // Every interpolated value is text: a name with & or < must not break the XHTML.
    cfg.setOutputFormat(XHTMLOutputFormat.INSTANCE);
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setLogTemplateExceptions(false);
    cfg.setWrapUncheckedExceptions(true);
  }

  @Override
  public Html toHTML(Report report) {
    return toHTML(report, ReportStyle.DEFAULT);
  }

  @Override
  public Html toHTML(Report report, ReportStyle style) {
    try {
      Template template = cfg.getTemplate("report.ftl");

      Map<String, Object> model = new HashMap<>();
      model.put("report", report);
      model.put("meta", buildMeta(report));
      model.put("s", style.toTemplateMap());

      StringWriter writer = new StringWriter();
      template.process(model, writer);
      return new Html(writer.toString());
    } catch (Exception e) {
      throw new ReportExportException("Failed to render HTML from report: " + report.id(), e);
    }
  }

  @Override
  public Html toHTML(InvoiceDocument invoice, ReportStyle style) {
    try {
      Template template = cfg.getTemplate("invoice.ftl");
      Map<String, Object> model = new HashMap<>();
      model.put("doc", invoice);
      model.put("s", style.toTemplateMap());
      StringWriter writer = new StringWriter();
      template.process(model, writer);
      return new Html(writer.toString());
    } catch (Exception e) {
      throw new ReportExportException("Failed to render invoice " + invoice.number(), e);
    }
  }

  @Override
  public PDF toPDF(Html html) {
    try {
      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocumentFromString(html.html());
      renderer.layout();

      ByteArrayOutputStream out = new ByteArrayOutputStream();
      renderer.createPDF(out);
      out.close();

      return new PDF(out.toByteArray());
    } catch (Exception e) {
      throw new ReportExportException("Failed to generate PDF", e);
    }
  }

  // ── Build the "meta" map for the template ────────────────────────────

  private Map<String, Object> buildMeta(Report report) {
    Map<String, Object> meta = new HashMap<>();

    meta.put("createdAt", CREATED_FMT.format(report.createdAt()));
    meta.put("defaultColors", Arrays.asList(DEFAULT_COLORS));

    // Page geometry
    setPageMeta(meta, report);
    // Header / footer
    setHeaderFooterMeta(meta, report);
    // Precomputed chart data
    precomputeChartData(meta, report);

    return meta;
  }

  private void setPageMeta(Map<String, Object> meta, Report report) {
    DocumentStyle style = report.documentStyle();

    String pageSize = "letter";
    String orientation = "portrait";
    double marginTop = 15, marginRight = 12, marginBottom = 15, marginLeft = 12;

    if (style != null && style.page() != null) {
      DocumentStyle.PageConfig page = style.page();
      pageSize =
          switch (page.size()) {
            case A4 -> "A4";
            case A3 -> "A3";
            case LEGAL -> "legal";
            case TABLOID -> "11in 17in";
            case LETTER -> "letter";
          };
      orientation =
          switch (page.orientation()) {
            case PORTRAIT -> "portrait";
            case LANDSCAPE -> "landscape";
          };
      if (page.margins() != null) {
        marginTop = page.margins().topMm();
        marginRight = page.margins().rightMm();
        marginBottom = page.margins().bottomMm();
        marginLeft = page.margins().leftMm();
      }
    }

    meta.put("pageSize", pageSize);
    meta.put("orientation", orientation);
    meta.put("marginTop", marginTop);
    meta.put("marginRight", marginRight);
    meta.put("marginBottom", marginBottom);
    meta.put("marginLeft", marginLeft);
  }

  private void setHeaderFooterMeta(Map<String, Object> meta, Report report) {
    DocumentStyle style = report.documentStyle();

    boolean hasHeader = false;
    boolean hasFooter = false;
    // Header fields — CompanyProfile takes precedence over Header fields
    String headerIconUrl = "";
    String headerLogoUrl = "";
    String headerCompanyName = "";
    String headerSubtitle = "";
    // Cover fields
    String coverLogoUrl = "";
    String coverPhotoUrl = "";
    String coverPhotoCaption = "";
    boolean hasCoverPhoto = false;
    boolean hasCompanyProfile = false;
    // Company profile fields
    String companyTagline = "";
    String companyRegistration = "";
    String companyAddress = "";
    String companyEmail = "";
    String companyPhone = "";
    String companyWebsite = "";
    // Footer fields
    String footerLeft = "", footerCenter = "", footerRight = "";
    boolean showPageNumbers = true;

    if (style != null) {
      // Company profile (preferred source for branding)
      CompanyProfile profile = style.companyProfile();
      if (profile != null) {
        hasCompanyProfile = true;
        headerCompanyName = Objects.toString(profile.name(), "");
        coverLogoUrl = Objects.toString(profile.logoUrl(), "");
        headerIconUrl = Objects.toString(profile.iconUrl(), "");
        companyTagline = Objects.toString(profile.tagline(), "");
        companyRegistration = Objects.toString(profile.registrationInfo(), "");
        companyAddress = Objects.toString(profile.address(), "");
        companyEmail = Objects.toString(profile.email(), "");
        companyPhone = Objects.toString(profile.phone(), "");
        companyWebsite = Objects.toString(profile.website(), "");
        // If icon is set, use it for running header; otherwise fall back to logo
        if (headerIconUrl.isEmpty()) {
          headerIconUrl = coverLogoUrl;
        }
        hasHeader = true;
      }

      // Cover photo
      if (style.coverPhoto() != null) {
        hasCoverPhoto = true;
        coverPhotoUrl = Objects.toString(style.coverPhoto().imageUrl(), "");
        coverPhotoCaption = Objects.toString(style.coverPhoto().caption(), "");
      }

      // Header (fills gaps not covered by CompanyProfile)
      if (style.header() != null) {
        hasHeader = true;
        if (headerLogoUrl.isEmpty() && headerIconUrl.isEmpty()) {
          headerIconUrl = Objects.toString(style.header().logoUrl(), "");
        }
        if (coverLogoUrl.isEmpty()) {
          coverLogoUrl = Objects.toString(style.header().logoUrl(), "");
        }
        if (headerCompanyName.isEmpty()) {
          headerCompanyName = Objects.toString(style.header().companyName(), "");
        }
        if (headerSubtitle.isEmpty()) {
          headerSubtitle = Objects.toString(style.header().subtitle(), "");
        }
      }

      // Footer
      if (style.footer() != null) {
        hasFooter = true;
        footerLeft = Objects.toString(style.footer().leftText(), "");
        footerCenter = Objects.toString(style.footer().centerText(), "");
        footerRight = Objects.toString(style.footer().rightText(), "");
        showPageNumbers = style.footer().showPageNumbers();
      }
    }

    meta.put("hasHeader", hasHeader);
    meta.put("hasFooter", hasFooter);
    meta.put("headerIconUrl", headerIconUrl);
    meta.put("headerLogoUrl", headerLogoUrl);
    meta.put("headerCompanyName", headerCompanyName);
    meta.put("headerSubtitle", headerSubtitle);
    meta.put("coverLogoUrl", coverLogoUrl);
    meta.put("hasCoverPhoto", hasCoverPhoto);
    meta.put("coverPhotoUrl", coverPhotoUrl);
    meta.put("coverPhotoCaption", coverPhotoCaption);
    meta.put("hasCompanyProfile", hasCompanyProfile);
    meta.put("companyTagline", companyTagline);
    meta.put("companyRegistration", companyRegistration);
    meta.put("companyAddress", companyAddress);
    meta.put("companyEmail", companyEmail);
    meta.put("companyPhone", companyPhone);
    meta.put("companyWebsite", companyWebsite);
    meta.put("footerLeft", footerLeft);
    meta.put("footerCenter", footerCenter);
    meta.put("footerRight", footerRight);
    meta.put("showPageNumbers", showPageNumbers);
  }

  /**
   * Groups each section's visualizations for the template, by section position: consecutive KPI
   * cards are batched into rows of 3, and every other visualization is a group of its own carrying
   * the figures the template needs ({@code calc}). The figures belong to the chart itself, so two
   * charts or sections with the same title never share them.
   */
  private void precomputeChartData(Map<String, Object> meta, Report report) {
    List<List<Map<String, Object>>> sectionGroups = new ArrayList<>();
    for (ReportSection section : report.sections()) {
      List<Map<String, Object>> groups = new ArrayList<>();
      List<Visualization> kpiBatch = new ArrayList<>();

      for (Visualization viz : section.visualizations()) {
        if (viz.type() == VisualizationType.KPI_CARD) {
          kpiBatch.add(viz);
          if (kpiBatch.size() == 3) {
            groups.add(kpiRow(kpiBatch));
            kpiBatch.clear();
          }
        } else {
          if (!kpiBatch.isEmpty()) {
            groups.add(kpiRow(kpiBatch));
            kpiBatch.clear();
          }
          Map<String, Object> group = new HashMap<>();
          group.put("type", "single");
          group.put("items", List.of(viz));
          group.put("calc", calc(viz));
          groups.add(group);
        }
      }
      if (!kpiBatch.isEmpty()) groups.add(kpiRow(kpiBatch));
      sectionGroups.add(groups);
    }
    meta.put("sectionGroups", sectionGroups);
  }

  private static Map<String, Object> kpiRow(List<Visualization> batch) {
    return Map.of("type", "kpi_row", "items", new ArrayList<>(batch), "calc", Map.of());
  }

  /** The figures one visualization needs: its largest value, its total, its column totals. */
  private static Map<String, Object> calc(Visualization viz) {
    Map<String, Object> calc = new HashMap<>();
    switch (viz) {
      case BarChart bar ->
          calc.put("max", max(bar.bars().stream().mapToDouble(CategoricalData::value).max()));
      case PieChart pie ->
          calc.put("total", pie.segments().stream().mapToDouble(CategoricalData::value).sum());
      case DonutChart donut ->
          calc.put("total", donut.segments().stream().mapToDouble(CategoricalData::value).sum());
      case HeatMap hm -> {
        double max = 0;
        for (double[] row : hm.intensityMatrix()) for (double v : row) max = Math.max(max, v);
        calc.put("max", max);
      }
      case FunnelChart funnel ->
          calc.put(
              "max", max(funnel.stages().stream().mapToDouble(FunnelChart.Stage::value).max()));
      case StackedBarChart stacked -> {
        List<Double> totals = new ArrayList<>();
        for (int i = 0; i < stacked.categories().size(); i++) {
          double sum = 0;
          for (StackedBarChart.Series s : stacked.series()) {
            if (i < s.values().size()) sum += s.values().get(i);
          }
          totals.add(sum);
        }
        calc.put("totals", totals);
      }
      case DataTable table -> calc.put("numeric", numericColumns(table));
      case TimeSeriesChart ts ->
          calc.put("labels", ts.data().stream().map(tp -> TP_FMT.format(tp.timestamp())).toList());
      default -> {
        /* no precomputation needed */
      }
    }
    return calc;
  }

  private static final java.util.regex.Pattern NUMBER =
      java.util.regex.Pattern.compile("[-(]?[\\d,]+(\\.\\d+)?[)%]?");

  /**
   * Per column, whether it holds numbers only (blank cells aside), so amounts line up on the right.
   */
  private static List<Boolean> numericColumns(DataTable table) {
    List<Boolean> numeric = new ArrayList<>();
    for (int c = 0; c < table.headers().size(); c++) {
      boolean any = false;
      boolean all = true;
      for (List<String> row : table.rows()) {
        if (c >= row.size() || row.get(c) == null || row.get(c).isBlank()) continue;
        any = true;
        if (!NUMBER.matcher(row.get(c).strip()).matches()) all = false;
      }
      numeric.add(any && all);
    }
    return numeric;
  }

  /** The largest value, or 0 when there is none; the template draws nothing against 0. */
  private static double max(java.util.OptionalDouble max) {
    return max.orElse(0);
  }
}
