package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record RadarChart(String title, List<String> axes, List<RadarSeries> series)
    implements Visualization {

  public record RadarSeries(String name, List<Double> values, String color) {
    public RadarSeries(String name, List<Double> values) {
      this(name, values, null);
    }
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.RADAR_CHART;
  }
}
