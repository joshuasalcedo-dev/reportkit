package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record TimeSeriesChart(
    String title, TimeGranularity granularity, String yAxisLabel, List<TimePoint> data)
    implements Visualization {
  @Override
  public VisualizationType type() {
    return VisualizationType.TIME_SERIES;
  }
}
