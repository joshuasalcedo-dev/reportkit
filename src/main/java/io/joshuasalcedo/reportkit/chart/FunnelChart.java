package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record FunnelChart(String title, List<Stage> stages) implements Visualization {

  public record Stage(String name, double value, String color) {
    public Stage(String name, double value) {
      this(name, value, null);
    }
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.FUNNEL_CHART;
  }
}
