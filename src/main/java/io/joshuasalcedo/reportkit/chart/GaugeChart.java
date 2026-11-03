package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record GaugeChart(
    String title,
    double value,
    double minValue,
    double maxValue,
    String unit,
    List<Threshold> thresholds)
    implements Visualization {

  public record Threshold(String label, double upTo, String color) {}

  public GaugeChart(String title, double value, double minValue, double maxValue) {
    this(title, value, minValue, maxValue, "", List.of());
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.GAUGE_CHART;
  }
}
