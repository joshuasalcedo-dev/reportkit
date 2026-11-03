package io.joshuasalcedo.reportkit.examples;

import static io.joshuasalcedo.reportkit.DataKitAPI.*;

import io.joshuasalcedo.reportkit.ReportAPI;
import io.joshuasalcedo.reportkit.report.DocumentStyle.Orientation;
import io.joshuasalcedo.reportkit.report.DocumentStyle.PageSize;
import io.joshuasalcedo.reportkit.report.Report;
import java.util.List;

/** A monthly sales report: a company cover, KPI cards, charts and a table with a total row. */
public final class SalesReportExample {

  private SalesReportExample() {}

  public static Report report() {
    return ReportAPI.report("Monthly Sales Report")
        .id("sales-2026-10")
        .author("Finance Team")
        .description("October 2026, all regions")
        .company(
            c ->
                c.name("Acme Corp")
                    .tagline("Tools for every workshop")
                    .address("100 Market Street, Springfield")
                    .contact("sales@acme.example", "+1 555 0100", "acme.example"))
        .header(h -> h.companyName("Acme Corp").subtitle("Internal"))
        .footer(f -> f.left("Acme Corp").center("Monthly Sales Report").pageNumbers(true))
        .page(p -> p.size(PageSize.LETTER).orientation(Orientation.PORTRAIT))
        .metadata("Period", "Oct 1 - Oct 31, 2026")
        .metadata("Currency", "USD")
        .section(
            "Highlights",
            s ->
                s.narrative("Revenue grew for the third month in a row, led by the West region.")
                    .add(
                        kpiCard("Revenue", "$482,300", "+12.4% vs September", true),
                        kpiCard("Orders", "3,918", "+6.1% vs September", true),
                        kpiCard("Refunds", "$9,120", "+2.3% vs September", false)))
        .section(
            "Revenue by region",
            s ->
                s.add(
                    barChart(
                        "Revenue by region",
                        "Region",
                        "USD",
                        List.of(
                            category("West", 168_400),
                            category("East", 131_900),
                            category("North", 102_700),
                            category("South", 79_300))),
                    donutChart(
                        "Revenue by channel",
                        "Total",
                        "$482K",
                        List.of(
                            category("Online", 251_000),
                            category("Retail", 149_300),
                            category("Wholesale", 82_000)))))
        .section(
            "Trend",
            s ->
                s.add(
                    lineChart(
                        "Revenue, last six months",
                        "Month",
                        "USD (thousands)",
                        List.of(
                            lineSeries(
                                "2026",
                                List.of(
                                    point("May", 361),
                                    point("Jun", 389),
                                    point("Jul", 402),
                                    point("Aug", 418),
                                    point("Sep", 429),
                                    point("Oct", 482))),
                            lineSeries(
                                "2025",
                                List.of(
                                    point("May", 310),
                                    point("Jun", 322),
                                    point("Jul", 335),
                                    point("Aug", 341),
                                    point("Sep", 356),
                                    point("Oct", 371)))))))
        .section(
            "Top products",
            s ->
                s.add(
                    dataTable(
                        "Top products",
                        List.of("Product", "Units", "Revenue", "Margin"),
                        List.of(
                            List.of("Cordless drill", "812", "96,628.00", "31%"),
                            List.of("Tool chest", "214", "74,686.00", "27%"),
                            List.of("Work bench", "167", "58,283.00", "24%"),
                            List.of("Socket set", "1,104", "44,160.00", "38%")),
                        List.of("Total", "2,297", "273,757.00", ""))))
        .build();
  }
}
