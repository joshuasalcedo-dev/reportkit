package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record AreaChart(
    String title, String xAxisLabel, String yAxisLabel, boolean stacked, List<AreaSeries> series)
    implements Visualization {

  public record AreaSeries(String name, List<LineChart.DataPoint> points, String color) {
    public AreaSeries(String name, List<LineChart.DataPoint> points) {
      this(name, points, null);
    }
  }

  public AreaChart(String title, String xAxisLabel, String yAxisLabel, List<AreaSeries> series) {
    this(title, xAxisLabel, yAxisLabel, false, series);
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.AREA_CHART;
  }
}
