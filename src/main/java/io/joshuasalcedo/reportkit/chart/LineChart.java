package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record LineChart(String title, String xAxisLabel, String yAxisLabel, List<LineSeries> series)
    implements Visualization {

  public record LineSeries(String name, List<DataPoint> points, String color) {
    public LineSeries(String name, List<DataPoint> points) {
      this(name, points, null);
    }
  }

  public record DataPoint(String label, double value) {}

  @Override
  public VisualizationType type() {
    return VisualizationType.LINE_CHART;
  }
}
