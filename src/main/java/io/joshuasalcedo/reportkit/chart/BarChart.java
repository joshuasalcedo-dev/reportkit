package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record BarChart(
    String title, String xAxisLabel, String yAxisLabel, List<CategoricalData> bars)
    implements Visualization {

  public BarChart(String title, String xAxisLabel, List<CategoricalData> bars) {
    this(title, xAxisLabel, "Value", bars);
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.BAR_CHART;
  }
}
