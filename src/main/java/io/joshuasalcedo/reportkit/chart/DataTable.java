package io.joshuasalcedo.reportkit.chart;

import java.util.List;

public record DataTable(
    String title, List<String> headers, List<List<String>> rows, List<String> totalRow)
    implements Visualization {

  /** Creates a data table without a total row. */
  public DataTable(String title, List<String> headers, List<List<String>> rows) {
    this(title, headers, rows, null);
  }

  @Override
  public VisualizationType type() {
    return VisualizationType.DATA_TABLE;
  }
}
