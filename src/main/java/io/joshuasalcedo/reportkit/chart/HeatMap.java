package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record HeatMap(
    String title,
    String xLabel,
    String yLabel,
    List<String> xCategories,
    List<String> yCategories,
    double[][] intensityMatrix)
    implements Visualization {

  public HeatMap(String title, String xLabel, String yLabel, double[][] intensityMatrix) {
    this(title, xLabel, yLabel, List.of(), List.of(), intensityMatrix);
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.HEAT_MAP;
  }
}
