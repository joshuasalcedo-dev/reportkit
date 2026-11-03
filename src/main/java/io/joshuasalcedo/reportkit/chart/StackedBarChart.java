package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record StackedBarChart(
    String title,
    String xAxisLabel,
    String yAxisLabel,
    List<String> categories,
    List<Series> series)
    implements Visualization {

  public record Series(String name, List<Double> values, String color) {
    public Series(String name, List<Double> values) {
      this(name, values, null);
    }
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.STACKED_BAR_CHART;
  }
}
