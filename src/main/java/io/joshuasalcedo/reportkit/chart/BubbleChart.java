package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record BubbleChart(
    String title, String xAxisLabel, String yAxisLabel, String sizeLabel, List<Bubble> bubbles)
    implements Visualization {

  public record Bubble(String label, double x, double y, double size, String color) {
    public Bubble(String label, double x, double y, double size) {
      this(label, x, y, size, null);
    }
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.BUBBLE_CHART;
  }
}
