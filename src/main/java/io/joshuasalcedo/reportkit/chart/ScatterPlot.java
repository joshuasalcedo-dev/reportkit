package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record ScatterPlot(
    String title, String xAxisLabel, String yAxisLabel, List<ScatterSeries> series)
    implements Visualization {

  public record ScatterSeries(String name, List<Point> points, String color) {
    public ScatterSeries(String name, List<Point> points) {
      this(name, points, null);
    }
  }

  public record Point(double x, double y) {}

  @Override
  public VisualizationType type() {
    return VisualizationType.SCATTER_PLOT;
  }
}
