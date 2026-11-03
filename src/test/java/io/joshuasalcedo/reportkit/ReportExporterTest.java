package io.joshuasalcedo.reportkit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static io.joshuasalcedo.reportkit.DataKitAPI.dataTable;
import static io.joshuasalcedo.reportkit.DataKitAPI.kpiCard;

import java.util.List;
import org.junit.jupiter.api.Test;
import io.joshuasalcedo.reportkit.chart.BarChart;
import io.joshuasalcedo.reportkit.chart.CategoricalData;
import io.joshuasalcedo.reportkit.chart.GaugeChart;
import io.joshuasalcedo.reportkit.chart.PieChart;
import io.joshuasalcedo.reportkit.chart.RadarChart;
import io.joshuasalcedo.reportkit.chart.StackedBarChart;
import io.joshuasalcedo.reportkit.report.Report;
import io.joshuasalcedo.reportkit.report.ReportSection;

class ReportExporterTest {

  @Test
  void textWithMarkupCharactersIsEscapedAndStillRendersAsPdf() {
    var report =
        ReportAPI.report("Profit & Loss")
            .company(c -> c.name("Smith & Sons <Books>"))
            .section(
                "Income & expenses",
                s ->
                    s.narrative("Rent < 50% of income")
                        .add(kpiCard("Net & income", "$1,000", "up", true))
                        .add(
                            dataTable(
                                "Lines",
                                List.of("Account", "Amount"),
                                List.of(List.of("Food & dining", "12.00")))))
            .build();

    var exporter = ReportExporter.createDefault();
    var html = exporter.toHTML(report).html();

    assertTrue(html.contains("Smith &amp; Sons &lt;Books&gt;"), html);
    assertTrue(html.contains("Food &amp; dining"));
    assertFalse(html.contains("Smith & Sons"));
    assertTrue(exporter.toPDF(new Html(html)).bytes().length > 0);
  }

  final ReportExporter exporter = ReportExporter.createDefault();

  static Report of(ReportSection... sections) {
    return new Report("r", "Test", "me", List.of(sections));
  }

  @Test
  void twoBarChartsWithTheSameTitleEachScaleToTheirOwnLargestValue() {
    var small = new BarChart("Revenue", "Item", List.of(new CategoricalData("A", 5)));
    var large =
        new BarChart(
            "Revenue",
            "Item",
            List.of(new CategoricalData("B", 100), new CategoricalData("C", 50)));
    var html = exporter.toHTML(of(new ReportSection("S", List.of(small, large)))).html();
    // A is the largest of its own chart, so it is full width, not 5% of the other chart's 100.
    assertTrue(html.contains("width:100%;\">&#160;"), html);
    assertTrue(html.contains("width:50%;"), html);
    assertFalse(html.contains("width:5%;"), html);
  }

  @Test
  void twoSectionsWithTheSameHeadingEachShowTheirOwnCharts() {
    var html =
        exporter
            .toHTML(
                of(
                    new ReportSection("Same", List.of(new PieChart("First pie", pieOf("x")))),
                    new ReportSection("Same", List.of(new PieChart("Second pie", pieOf("y"))))))
            .html();
    assertTrue(html.contains("First pie"), html);
    assertTrue(html.contains("Second pie"), html);
  }

  @Test
  void zerosAndShortSeriesStillRender() {
    var zeros = new BarChart("Nothing sold", "Item", List.of(new CategoricalData("A", 0)));
    var flatGauge = new GaugeChart("Flat", 3, 5, 5);
    var radar =
        new RadarChart(
            "Short",
            List.of("a", "b", "c"),
            List.of(new RadarChart.RadarSeries("s", List.of(1.0))));
    var stacked =
        new StackedBarChart(
            "Short stack",
            "x",
            "y",
            List.of("one", "two"),
            List.of(new StackedBarChart.Series("s", List.of(1.0))));
    var report = of(new ReportSection("S", List.of(zeros, flatGauge, radar, stacked)));
    var html = exporter.toHTML(report).html();
    assertTrue(html.contains("Nothing sold"));
    assertTrue(exporter.toPDF(new Html(html)).bytes().length > 0);
  }

  @Test
  void amountsCarryNoDollarSignTheReportDidNotAskFor() {
    var html =
        exporter
            .toHTML(of(new ReportSection("S", List.of(new PieChart("Count", pieOf("x"))))))
            .html();
    assertFalse(html.contains("$"), html);
  }

  @Test
  void aPdfPrintsItsSizeNotItsBytes() {
    assertEquals("PDF{3 bytes}", new PDF(new byte[] {1, 2, 3}).toString());
  }

  static List<CategoricalData> pieOf(String label) {
    return List.of(new CategoricalData(label, 2), new CategoricalData(label + "2", 3));
  }
}
