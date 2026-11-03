package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record DonutChart(
    String title, String centerLabel, String centerValue, List<CategoricalData> segments)
    implements Visualization {

  public DonutChart(String title, List<CategoricalData> segments) {
    this(title, "", "", segments);
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.DONUT_CHART;
  }
}
