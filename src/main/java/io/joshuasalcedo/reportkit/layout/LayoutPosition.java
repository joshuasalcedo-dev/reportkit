package io.joshuasalcedo.reportkit.layout;

/**
 * Grid-based positioning for a visualization within a dashboard.
 *
 * @param row zero-based row index
 * @param column zero-based column index
 * @param rowSpan how many rows this cell spans (default 1)
 * @param colSpan how many columns this cell spans (default 1)
 */
public record LayoutPosition(int row, int column, int rowSpan, int colSpan) {

  public LayoutPosition(int row, int column) {
    this(row, column, 1, 1);
  }
}
