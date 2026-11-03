package io.joshuasalcedo.reportkit.examples;

import static io.joshuasalcedo.reportkit.DataKitAPI.*;

import io.joshuasalcedo.reportkit.ReportAPI;
import io.joshuasalcedo.reportkit.report.Report;
import java.util.List;

/** One of each chart: stacked bars, pie, radar, funnel, gauge and heat map. */
public final class ChartGalleryExample {

  private ChartGalleryExample() {}

  public static Report report() {
    return ReportAPI.report("Chart Gallery")
        .author("ReportKit")
        .description("Every chart type ReportKit draws")
        .footer(f -> f.center("ReportKit chart gallery").pageNumbers(true))
        .section(
            "Comparisons",
            s ->
                s.add(
                    stackedBarChart(
                        "Tickets by priority",
                        "Week",
                        "Tickets",
                        List.of("W1", "W2", "W3", "W4"),
                        List.of(
                            stackedSeries("Low", List.of(42.0, 38.0, 45.0, 40.0)),
                            stackedSeries("Medium", List.of(25.0, 30.0, 22.0, 28.0)),
                            stackedSeries("High", List.of(8.0, 12.0, 6.0, 9.0)))),
                    pieChart(
                        "Traffic sources",
                        List.of(
                            category("Search", 46),
                            category("Direct", 27),
                            category("Social", 18),
                            category("Email", 9)))))
        .section(
            "Shapes",
            s ->
                s.add(
                    radarChart(
                        "Team skills",
                        List.of("Design", "Backend", "Frontend", "Testing", "Ops"),
                        List.of(
                            radarSeries("Team A", List.of(8.0, 6.0, 7.0, 5.0, 4.0)),
                            radarSeries("Team B", List.of(5.0, 8.0, 4.0, 7.0, 8.0)))),
                    funnelChart(
                        "Sign-up funnel",
                        List.of(
                            stage("Visited", 12_000),
                            stage("Signed up", 3_400),
                            stage("Activated", 1_900),
                            stage("Paid", 640)))))
        .section(
            "Levels",
            s ->
                s.add(
                    gaugeChart(
                        "Server load",
                        72,
                        0,
                        100,
                        "%",
                        List.of(
                            threshold("Normal", 60, "#22c55e"),
                            threshold("Busy", 85, "#f59e0b"),
                            threshold("Overloaded", 100, "#ef4444"))),
                    heatMap(
                        "Orders by day and hour",
                        "Hour",
                        "Day",
                        List.of("9", "12", "15", "18"),
                        List.of("Mon", "Tue", "Wed", "Thu", "Fri"),
                        new double[][] {
                          {4, 9, 7, 3}, {5, 11, 8, 4}, {6, 12, 10, 5}, {5, 10, 9, 6}, {3, 8, 11, 9}
                        })))
        .build();
  }
}
