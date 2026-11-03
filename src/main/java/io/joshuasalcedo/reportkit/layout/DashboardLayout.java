package io.joshuasalcedo.reportkit.layout;

/**
 * Top-level layout configuration for a dashboard.
 *
 * @param type how panels are arranged
 * @param columns number of grid columns (relevant for GRID type)
 * @param gapPx spacing between panels in pixels
 */
public record DashboardLayout(LayoutType type, int columns, int gapPx) {

  public DashboardLayout(LayoutType type, int columns) {
    this(type, columns, 16);
  }

  public DashboardLayout(LayoutType type) {
    this(type, deriveColumns(type), 16);
  }

  private static int deriveColumns(LayoutType type) {
    return switch (type) {
      case SINGLE_COLUMN -> 1;
      case TWO_COLUMN -> 2;
      case THREE_COLUMN -> 3;
      case GRID, FREEFORM -> 12;
    };
  }
}
