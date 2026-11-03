package io.joshuasalcedo.reportkit.chart;

/** Sealed interface for all report visualization components. */
public sealed interface Visualization
    permits PieChart,
        DonutChart,
        BarChart,
        StackedBarChart,
        LineChart,
        AreaChart,
        TimeSeriesChart,
        ScatterPlot,
        BubbleChart,
        HeatMap,
        RadarChart,
        FunnelChart,
        GaugeChart,
        KpiCard,
        DataTable {

  String title();

  VisualizationType type();

  default String description() {
    return "";
  }

  default String subtitle() {
    return "";
  }
}
