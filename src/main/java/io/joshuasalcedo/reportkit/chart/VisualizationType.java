package io.joshuasalcedo.reportkit.chart;

public enum VisualizationType {
  PIE_CHART("Pie Chart", Category.PROPORTION, "Displays parts of a whole as wedge segments"),
  DONUT_CHART(
      "Donut Chart",
      Category.PROPORTION,
      "Pie chart variant with a hollow center for summary text"),
  BAR_CHART(
      "Bar Chart", Category.COMPARISON, "Compares values across categories using vertical bars"),
  STACKED_BAR_CHART(
      "Stacked Bar Chart",
      Category.COMPARISON,
      "Shows composition within each category as stacked segments"),
  LINE_CHART(
      "Line Chart", Category.TREND, "Connects data points to reveal trends over a continuous axis"),
  AREA_CHART(
      "Area Chart", Category.TREND, "Line chart with the region below filled to emphasize volume"),
  TIME_SERIES(
      "Time Series", Category.TREND, "Plots values against timestamps at a fixed granularity"),
  SCATTER_PLOT(
      "Scatter Plot", Category.CORRELATION, "Plots individual data points on two numeric axes"),
  BUBBLE_CHART(
      "Bubble Chart",
      Category.CORRELATION,
      "Scatter plot with a third dimension encoded as bubble size"),
  HEAT_MAP(
      "Heat Map", Category.DISTRIBUTION, "Displays intensity values in a matrix of colored cells"),
  RADAR_CHART(
      "Radar Chart",
      Category.COMPARISON,
      "Compares multiple variables on axes radiating from a center"),
  FUNNEL_CHART(
      "Funnel Chart", Category.FLOW, "Shows progressive reduction through sequential stages"),
  GAUGE_CHART("Gauge Chart", Category.KPI, "Displays a single metric against a min/max range"),
  KPI_CARD("KPI Card", Category.KPI, "Highlights a key metric with trend indication"),
  DATA_TABLE("Data Table", Category.DETAIL, "Presents raw data in rows and columns");

  private final String displayName;
  private final Category category;
  private final String description;

  VisualizationType(String displayName, Category category, String description) {
    this.displayName = displayName;
    this.category = category;
    this.description = description;
  }

  public String displayName() {
    return displayName;
  }

  public Category category() {
    return category;
  }

  public String description() {
    return description;
  }

  public enum Category {
    PROPORTION,
    COMPARISON,
    TREND,
    CORRELATION,
    DISTRIBUTION,
    FLOW,
    KPI,
    DETAIL
  }
}
