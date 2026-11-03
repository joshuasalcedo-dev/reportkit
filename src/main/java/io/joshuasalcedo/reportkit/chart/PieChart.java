package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record PieChart(String title, List<CategoricalData> segments) implements Visualization {
  @Override
  public VisualizationType type() {
    return VisualizationType.PIE_CHART;
  }
}
