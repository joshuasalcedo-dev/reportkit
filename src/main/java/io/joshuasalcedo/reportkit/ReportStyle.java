package io.joshuasalcedo.reportkit;

import java.util.HashMap;
import java.util.Map;

/**
 * Visual style configuration for report rendering.
 *
 * <p>Controls all CSS-level appearance of the generated HTML/PDF: typography, colors, image sizing,
 * spacing, borders, and component-specific styling. Every value has a sensible default; override
 * only what you need.
 *
 * <pre>{@code
 * // Use a preset
 * ReportStyle style = ReportStyle.COMPACT;
 *
 * // Or customize with the builder
 * ReportStyle style = ReportStyle.builder()
 *     .fontFamily("Inter, sans-serif")
 *     .primaryColor("#2563eb")
 *     .coverLogoHeight(80)
 *     .borderRadius(12)
 *     .kpiValueSize("24pt")
 *     .build();
 *
 * // Use in export chain
 * ReportExporterAPI.report("My Report")
 *     .style(style)
 *     .section(...)
 *     .export(Path.of("output"));
 * }</pre>
 *
 * @author JoshuaSalcedo
 */
public record ReportStyle(

    // ── Typography ─────────────────────────────────────────────────────

    /** CSS font-family for the entire report. */
    String fontFamily,
    /** Body text font size (e.g. "10pt"). */
    String bodyFontSize,
    /** Section heading font size (e.g. "14pt"). */
    String headingFontSize,
    /** Chart/visualization title font size (e.g. "11pt"). */
    String chartTitleFontSize,
    /** KPI card main value font size (e.g. "18pt"). */
    String kpiValueFontSize,
    /** Gauge chart value font size (e.g. "28pt"). */
    String gaugeValueFontSize,
    /** Cover page company name font size (e.g. "22pt"). */
    String coverCompanyFontSize,
    /** Cover page report title font size (e.g. "16pt"). */
    String coverTitleFontSize,
    /** Small/label font size (e.g. "8pt"). */
    String smallFontSize,
    /** Tiny/caption font size (e.g. "7pt"). */
    String tinyFontSize,
    /** Table data font size (e.g. "8pt"). */
    String tableFontSize,
    /** Body text line height. */
    String lineHeight,

    // ── Colors ─────────────────────────────────────────────────────────

    /** Primary accent color (cover border, gauge fill, etc.). */
    String primaryColor,
    /** Primary body text color. */
    String textColor,
    /** Dark text color (headings, titles, KPI values). */
    String darkTextColor,
    /** Secondary/label text color. */
    String mutedTextColor,
    /** Tertiary/caption text color. */
    String lightTextColor,
    /** Section heading text color. */
    String headingColor,
    /** Primary border color. */
    String borderColor,
    /** Light border color (table cell dividers). */
    String lightBorderColor,
    /** Strong border color (total row, cover section). */
    String strongBorderColor,
    /** Section heading underline color. */
    String headingBorderColor,
    /** Card/box background color. */
    String cardBackground,
    /** Page background color. */
    String pageBackground,
    /** KPI/trend positive color (green). */
    String positiveColor,
    /** KPI/trend negative color (red). */
    String negativeColor,

    // ── Image Sizing ───────────────────────────────────────────────────

    /** Cover page logo height in pixels. */
    int coverLogoHeight,
    /** Running header icon size in pixels. */
    int headerIconSize,
    /** Cover photo max height in pixels. */
    int coverPhotoMaxHeight,

    // ── Spacing ────────────────────────────────────────────────────────

    /** Gap between sections in pixels. */
    int sectionGap,
    /** Padding inside visualization cards in pixels. */
    int cardPadding,
    /** Padding inside KPI cards (e.g. "14px 16px"). */
    String kpiPadding,
    /** Gap below chart titles in pixels. */
    int chartTitleGap,
    /** Cover section bottom margin in pixels. */
    int coverBottomMargin,

    // ── Borders ────────────────────────────────────────────────────────

    /** Card/visualization border radius in pixels. */
    int borderRadius,
    /** Info box (company profile, metadata) border radius in pixels. */
    int infoBoxRadius,
    /** Header bottom border width in pixels. */
    int headerBorderWidth,
    /** Cover section bottom border width in pixels. */
    int coverBorderWidth,

    // ── Component: Bar Chart ───────────────────────────────────────────

    /** Bar height in pixels. */
    int barHeight,
    /** Bar border radius in pixels. */
    int barRadius,

    // ── Component: Gauge Chart ─────────────────────────────────────────

    /** Gauge progress bar height in pixels. */
    int gaugeHeight,
    /** Gauge bar border radius in pixels. */
    int gaugeRadius,

    // ── Component: Funnel Chart ────────────────────────────────────────

    /** Funnel stage bar height in pixels. */
    int funnelBarHeight,

    // ── Component: Data Table ──────────────────────────────────────────

    /** Whether to use alternating row colors. */
    boolean tableStriped,
    /** Alternating row background (same as cardBackground by default). */
    String tableStripeColor,

    // ── Layout ──────────────────────────────────────────────────────────

    /** Whether each report section starts on a new page in PDF output. */
    boolean sectionPageBreak) {

  // ── Presets ─────────────────────────────────────────────────────────

  /** Default style — clean, professional, balanced spacing with page breaks per section. */
  public static final ReportStyle DEFAULT = builder().build();

  /** Compact style — smaller fonts, tighter spacing for dense data. */
  public static final ReportStyle COMPACT =
      builder()
          .bodyFontSize("9pt")
          .headingFontSize("12pt")
          .chartTitleFontSize("10pt")
          .kpiValueFontSize("16pt")
          .gaugeValueFontSize("24pt")
          .coverCompanyFontSize("18pt")
          .coverTitleFontSize("14pt")
          .smallFontSize("7pt")
          .tinyFontSize("6pt")
          .tableFontSize("7pt")
          .sectionGap(20)
          .cardPadding(12)
          .kpiPadding("10px 12px")
          .chartTitleGap(8)
          .coverBottomMargin(20)
          .coverLogoHeight(48)
          .headerIconSize(20)
          .barHeight(16)
          .gaugeHeight(10)
          .funnelBarHeight(14)
          .build();

  /** Modern style — larger typography, rounded corners, spacious. */
  public static final ReportStyle MODERN =
      builder()
          .fontFamily("'Segoe UI', system-ui, -apple-system, sans-serif")
          .bodyFontSize("11pt")
          .headingFontSize("16pt")
          .chartTitleFontSize("12pt")
          .kpiValueFontSize("22pt")
          .gaugeValueFontSize("32pt")
          .coverCompanyFontSize("26pt")
          .coverTitleFontSize("18pt")
          .lineHeight("1.6")
          .primaryColor("#2563eb")
          .headingColor("#1e40af")
          .headingBorderColor("#bfdbfe")
          .borderRadius(12)
          .infoBoxRadius(10)
          .sectionGap(36)
          .cardPadding(20)
          .kpiPadding("18px 20px")
          .chartTitleGap(16)
          .coverBottomMargin(36)
          .coverLogoHeight(72)
          .barHeight(24)
          .barRadius(6)
          .gaugeHeight(16)
          .gaugeRadius(8)
          .funnelBarHeight(20)
          .build();

  /** Corporate style — serif fonts, conservative colors. */
  public static final ReportStyle CORPORATE =
      builder()
          .fontFamily("'Georgia', 'Times New Roman', serif")
          .primaryColor("#1e3a5f")
          .headingColor("#1e3a5f")
          .headingBorderColor("#cbd5e1")
          .darkTextColor("#0f172a")
          .borderRadius(4)
          .infoBoxRadius(4)
          .barRadius(2)
          .gaugeRadius(4)
          .build();

  /** Minimal style — no card borders, clean lines. */
  public static final ReportStyle MINIMAL =
      builder()
          .borderRadius(0)
          .infoBoxRadius(0)
          .barRadius(0)
          .gaugeRadius(0)
          .headerBorderWidth(1)
          .coverBorderWidth(1)
          .primaryColor("#6b7280")
          .headingBorderColor("#e5e7eb")
          .build();

  // ── Builder ─────────────────────────────────────────────────────────

  /**
   * Creates a new builder with all default values.
   *
   * @return a new {@link Builder}
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Creates a builder pre-filled with this style's values, for overriding.
   *
   * @return a new {@link Builder} copying this style
   */
  public Builder toBuilder() {
    return new Builder()
        .fontFamily(fontFamily)
        .bodyFontSize(bodyFontSize)
        .headingFontSize(headingFontSize)
        .chartTitleFontSize(chartTitleFontSize)
        .kpiValueFontSize(kpiValueFontSize)
        .gaugeValueFontSize(gaugeValueFontSize)
        .coverCompanyFontSize(coverCompanyFontSize)
        .coverTitleFontSize(coverTitleFontSize)
        .smallFontSize(smallFontSize)
        .tinyFontSize(tinyFontSize)
        .tableFontSize(tableFontSize)
        .lineHeight(lineHeight)
        .primaryColor(primaryColor)
        .textColor(textColor)
        .darkTextColor(darkTextColor)
        .mutedTextColor(mutedTextColor)
        .lightTextColor(lightTextColor)
        .headingColor(headingColor)
        .borderColor(borderColor)
        .lightBorderColor(lightBorderColor)
        .strongBorderColor(strongBorderColor)
        .headingBorderColor(headingBorderColor)
        .cardBackground(cardBackground)
        .pageBackground(pageBackground)
        .positiveColor(positiveColor)
        .negativeColor(negativeColor)
        .coverLogoHeight(coverLogoHeight)
        .headerIconSize(headerIconSize)
        .coverPhotoMaxHeight(coverPhotoMaxHeight)
        .sectionGap(sectionGap)
        .cardPadding(cardPadding)
        .kpiPadding(kpiPadding)
        .chartTitleGap(chartTitleGap)
        .coverBottomMargin(coverBottomMargin)
        .borderRadius(borderRadius)
        .infoBoxRadius(infoBoxRadius)
        .headerBorderWidth(headerBorderWidth)
        .coverBorderWidth(coverBorderWidth)
        .barHeight(barHeight)
        .barRadius(barRadius)
        .gaugeHeight(gaugeHeight)
        .gaugeRadius(gaugeRadius)
        .funnelBarHeight(funnelBarHeight)
        .tableStriped(tableStriped)
        .tableStripeColor(tableStripeColor)
        .sectionPageBreak(sectionPageBreak);
  }

  /**
   * Converts this style into a flat string map for template rendering.
   *
   * @return map of style property names to string values
   */
  public Map<String, Object> toTemplateMap() {
    Map<String, Object> m = new HashMap<>();
    // Typography
    m.put("fontFamily", fontFamily);
    m.put("bodyFontSize", bodyFontSize);
    m.put("headingFontSize", headingFontSize);
    m.put("chartTitleFontSize", chartTitleFontSize);
    m.put("kpiValueFontSize", kpiValueFontSize);
    m.put("gaugeValueFontSize", gaugeValueFontSize);
    m.put("coverCompanyFontSize", coverCompanyFontSize);
    m.put("coverTitleFontSize", coverTitleFontSize);
    m.put("smallFontSize", smallFontSize);
    m.put("tinyFontSize", tinyFontSize);
    m.put("tableFontSize", tableFontSize);
    m.put("lineHeight", lineHeight);
    // Colors
    m.put("primaryColor", primaryColor);
    m.put("textColor", textColor);
    m.put("darkTextColor", darkTextColor);
    m.put("mutedTextColor", mutedTextColor);
    m.put("lightTextColor", lightTextColor);
    m.put("headingColor", headingColor);
    m.put("borderColor", borderColor);
    m.put("lightBorderColor", lightBorderColor);
    m.put("strongBorderColor", strongBorderColor);
    m.put("headingBorderColor", headingBorderColor);
    m.put("cardBackground", cardBackground);
    m.put("pageBackground", pageBackground);
    m.put("positiveColor", positiveColor);
    m.put("negativeColor", negativeColor);
    // Image sizing
    m.put("coverLogoHeight", coverLogoHeight);
    m.put("headerIconSize", headerIconSize);
    m.put("coverPhotoMaxHeight", coverPhotoMaxHeight);
    // Spacing
    m.put("sectionGap", sectionGap);
    m.put("cardPadding", cardPadding);
    m.put("kpiPadding", kpiPadding);
    m.put("chartTitleGap", chartTitleGap);
    m.put("coverBottomMargin", coverBottomMargin);
    // Borders
    m.put("borderRadius", borderRadius);
    m.put("infoBoxRadius", infoBoxRadius);
    m.put("headerBorderWidth", headerBorderWidth);
    m.put("coverBorderWidth", coverBorderWidth);
    // Components
    m.put("barHeight", barHeight);
    m.put("barRadius", barRadius);
    m.put("gaugeHeight", gaugeHeight);
    m.put("gaugeRadius", gaugeRadius);
    m.put("funnelBarHeight", funnelBarHeight);
    m.put("tableStriped", tableStriped);
    m.put("tableStripeColor", tableStripeColor);
    // Layout
    m.put("sectionPageBreak", sectionPageBreak);
    return m;
  }

  // ── Builder class ──────────────────────────────────────────────────

  public static final class Builder {

    // Typography
    private String fontFamily = "Helvetica, Arial, sans-serif";
    private String bodyFontSize = "10pt";
    private String headingFontSize = "14pt";
    private String chartTitleFontSize = "11pt";
    private String kpiValueFontSize = "18pt";
    private String gaugeValueFontSize = "28pt";
    private String coverCompanyFontSize = "22pt";
    private String coverTitleFontSize = "16pt";
    private String smallFontSize = "8pt";
    private String tinyFontSize = "7pt";
    private String tableFontSize = "8pt";
    private String lineHeight = "1.5";

    // Colors
    private String primaryColor = "#3b82f6";
    private String textColor = "#1f2937";
    private String darkTextColor = "#111827";
    private String mutedTextColor = "#6b7280";
    private String lightTextColor = "#9ca3af";
    private String headingColor = "#1e3a5f";
    private String borderColor = "#e5e7eb";
    private String lightBorderColor = "#f3f4f6";
    private String strongBorderColor = "#374151";
    private String headingBorderColor = "#dbeafe";
    private String cardBackground = "#f9fafb";
    private String pageBackground = "#ffffff";
    private String positiveColor = "#059669";
    private String negativeColor = "#dc2626";

    // Image sizing
    private int coverLogoHeight = 60;
    private int headerIconSize = 24;
    private int coverPhotoMaxHeight = 180;

    // Spacing
    private int sectionGap = 20;
    private int cardPadding = 12;
    private String kpiPadding = "10px 12px";
    private int chartTitleGap = 8;
    private int coverBottomMargin = 16;

    // Borders
    private int borderRadius = 8;
    private int infoBoxRadius = 6;
    private int headerBorderWidth = 2;
    private int coverBorderWidth = 3;

    // Components
    private int barHeight = 20;
    private int barRadius = 4;
    private int gaugeHeight = 12;
    private int gaugeRadius = 6;
    private int funnelBarHeight = 16;
    private boolean tableStriped = true;
    private String tableStripeColor = "#f9fafb";

    // Layout
    private boolean sectionPageBreak = true;

    Builder() {}

    // ── Typography setters ─────────────────────────────────────────

    public Builder fontFamily(String v) {
      this.fontFamily = v;
      return this;
    }

    public Builder bodyFontSize(String v) {
      this.bodyFontSize = v;
      return this;
    }

    public Builder headingFontSize(String v) {
      this.headingFontSize = v;
      return this;
    }

    public Builder chartTitleFontSize(String v) {
      this.chartTitleFontSize = v;
      return this;
    }

    public Builder kpiValueFontSize(String v) {
      this.kpiValueFontSize = v;
      return this;
    }

    public Builder gaugeValueFontSize(String v) {
      this.gaugeValueFontSize = v;
      return this;
    }

    public Builder coverCompanyFontSize(String v) {
      this.coverCompanyFontSize = v;
      return this;
    }

    public Builder coverTitleFontSize(String v) {
      this.coverTitleFontSize = v;
      return this;
    }

    public Builder smallFontSize(String v) {
      this.smallFontSize = v;
      return this;
    }

    public Builder tinyFontSize(String v) {
      this.tinyFontSize = v;
      return this;
    }

    public Builder tableFontSize(String v) {
      this.tableFontSize = v;
      return this;
    }

    public Builder lineHeight(String v) {
      this.lineHeight = v;
      return this;
    }

    // ── Color setters ──────────────────────────────────────────────

    public Builder primaryColor(String v) {
      this.primaryColor = v;
      return this;
    }

    public Builder textColor(String v) {
      this.textColor = v;
      return this;
    }

    public Builder darkTextColor(String v) {
      this.darkTextColor = v;
      return this;
    }

    public Builder mutedTextColor(String v) {
      this.mutedTextColor = v;
      return this;
    }

    public Builder lightTextColor(String v) {
      this.lightTextColor = v;
      return this;
    }

    public Builder headingColor(String v) {
      this.headingColor = v;
      return this;
    }

    public Builder borderColor(String v) {
      this.borderColor = v;
      return this;
    }

    public Builder lightBorderColor(String v) {
      this.lightBorderColor = v;
      return this;
    }

    public Builder strongBorderColor(String v) {
      this.strongBorderColor = v;
      return this;
    }

    public Builder headingBorderColor(String v) {
      this.headingBorderColor = v;
      return this;
    }

    public Builder cardBackground(String v) {
      this.cardBackground = v;
      return this;
    }

    public Builder pageBackground(String v) {
      this.pageBackground = v;
      return this;
    }

    public Builder positiveColor(String v) {
      this.positiveColor = v;
      return this;
    }

    public Builder negativeColor(String v) {
      this.negativeColor = v;
      return this;
    }

    // ── Image sizing setters ───────────────────────────────────────

    public Builder coverLogoHeight(int v) {
      this.coverLogoHeight = v;
      return this;
    }

    public Builder headerIconSize(int v) {
      this.headerIconSize = v;
      return this;
    }

    public Builder coverPhotoMaxHeight(int v) {
      this.coverPhotoMaxHeight = v;
      return this;
    }

    // ── Spacing setters ────────────────────────────────────────────

    public Builder sectionGap(int v) {
      this.sectionGap = v;
      return this;
    }

    public Builder cardPadding(int v) {
      this.cardPadding = v;
      return this;
    }

    public Builder kpiPadding(String v) {
      this.kpiPadding = v;
      return this;
    }

    public Builder chartTitleGap(int v) {
      this.chartTitleGap = v;
      return this;
    }

    public Builder coverBottomMargin(int v) {
      this.coverBottomMargin = v;
      return this;
    }

    // ── Border setters ─────────────────────────────────────────────

    public Builder borderRadius(int v) {
      this.borderRadius = v;
      return this;
    }

    public Builder infoBoxRadius(int v) {
      this.infoBoxRadius = v;
      return this;
    }

    public Builder headerBorderWidth(int v) {
      this.headerBorderWidth = v;
      return this;
    }

    public Builder coverBorderWidth(int v) {
      this.coverBorderWidth = v;
      return this;
    }

    // ── Component setters ──────────────────────────────────────────

    public Builder barHeight(int v) {
      this.barHeight = v;
      return this;
    }

    public Builder barRadius(int v) {
      this.barRadius = v;
      return this;
    }

    public Builder gaugeHeight(int v) {
      this.gaugeHeight = v;
      return this;
    }

    public Builder gaugeRadius(int v) {
      this.gaugeRadius = v;
      return this;
    }

    public Builder funnelBarHeight(int v) {
      this.funnelBarHeight = v;
      return this;
    }

    public Builder tableStriped(boolean v) {
      this.tableStriped = v;
      return this;
    }

    public Builder tableStripeColor(String v) {
      this.tableStripeColor = v;
      return this;
    }

    // ── Layout setters ──────────────────────────────────────────────

    public Builder sectionPageBreak(boolean v) {
      this.sectionPageBreak = v;
      return this;
    }

    public ReportStyle build() {
      return new ReportStyle(
          fontFamily,
          bodyFontSize,
          headingFontSize,
          chartTitleFontSize,
          kpiValueFontSize,
          gaugeValueFontSize,
          coverCompanyFontSize,
          coverTitleFontSize,
          smallFontSize,
          tinyFontSize,
          tableFontSize,
          lineHeight,
          primaryColor,
          textColor,
          darkTextColor,
          mutedTextColor,
          lightTextColor,
          headingColor,
          borderColor,
          lightBorderColor,
          strongBorderColor,
          headingBorderColor,
          cardBackground,
          pageBackground,
          positiveColor,
          negativeColor,
          coverLogoHeight,
          headerIconSize,
          coverPhotoMaxHeight,
          sectionGap,
          cardPadding,
          kpiPadding,
          chartTitleGap,
          coverBottomMargin,
          borderRadius,
          infoBoxRadius,
          headerBorderWidth,
          coverBorderWidth,
          barHeight,
          barRadius,
          gaugeHeight,
          gaugeRadius,
          funnelBarHeight,
          tableStriped,
          tableStripeColor,
          sectionPageBreak);
    }
  }
}
