package io.joshuasalcedo.reportkit.layout;

import io.joshuasalcedo.reportkit.chart.Visualization;
import io.joshuasalcedo.reportkit.datasource.DataSource;

/**
 * A panel binds a visualization to its position in the dashboard and optionally to the data source
 * that feeds it.
 *
 * @param id unique identifier within the dashboard
 * @param visualization the chart/table/KPI to render
 * @param position where it sits in the grid
 * @param dataSource optional origin of the data (nullable)
 */
public record Panel(
    String id, Visualization visualization, LayoutPosition position, DataSource dataSource) {

  public Panel(String id, Visualization visualization, LayoutPosition position) {
    this(id, visualization, position, null);
  }
}
