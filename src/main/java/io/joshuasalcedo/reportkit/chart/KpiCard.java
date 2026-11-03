package io.joshuasalcedo.reportkit.chart;

public record KpiCard(String title, String mainValue, String subValue, boolean isPositiveTrend)
    implements Visualization {
  @Override
  public VisualizationType type() {
    return VisualizationType.KPI_CARD;
  }
}
