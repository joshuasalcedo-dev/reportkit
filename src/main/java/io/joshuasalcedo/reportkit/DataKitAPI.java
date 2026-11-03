package io.joshuasalcedo.reportkit;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import io.joshuasalcedo.reportkit.chart.*;
import io.joshuasalcedo.reportkit.datasource.DataSource;
import io.joshuasalcedo.reportkit.datasource.DataSourceType;
import io.joshuasalcedo.reportkit.layout.DashboardLayout;
import io.joshuasalcedo.reportkit.layout.LayoutPosition;
import io.joshuasalcedo.reportkit.layout.LayoutType;
import io.joshuasalcedo.reportkit.layout.Panel;
import io.joshuasalcedo.reportkit.report.CompanyProfile;
import io.joshuasalcedo.reportkit.report.Dashboard;
import io.joshuasalcedo.reportkit.report.DocumentStyle;
import io.joshuasalcedo.reportkit.report.DocumentStyle.*;
import io.joshuasalcedo.reportkit.report.Report;
import io.joshuasalcedo.reportkit.report.ReportSection;
import io.joshuasalcedo.reportkit.theme.Color;
import io.joshuasalcedo.reportkit.theme.ColorPalette;
import io.joshuasalcedo.reportkit.theme.FontSpec;
import io.joshuasalcedo.reportkit.theme.Theme;

/**
 * Single entry point to the DataKit model library.
 *
 * <p>Static facade providing factory methods for every model type so consumers need only one import
 * to build complete dashboards and reports:
 *
 * <pre>{@code
 * import static io.joshuasalcedo.reportkit.DataKitAPI.*;
 *
 * var chart = barChart("Revenue", "Quarter", List.of(
 *     category("Q1", 120_000),
 *     category("Q2", 185_000),
 *     category("Q3", 210_000)
 * ));
 *
 * var dash = dashboard("d1", "Sales Dashboard", List.of(
 *     panel(chart, 0, 0),
 *     panel(kpiCard("Profit", "$42K", "+8%", true), 0, 1)
 * ), grid(2), darkTheme());
 * }</pre>
 *
 * <p>This class cannot be instantiated. All methods are static.
 *
 * @author JoshuaSalcedo
 */
public final class DataKitAPI {

  private DataKitAPI() {}

  // ── Data points & series ────────────────────────────────────────────

  /**
   * Creates a labeled data point for bar charts, pie charts, and donut charts.
   *
   * @param label display label (e.g. "Q1", "North America")
   * @param value numeric value
   * @return a new {@link CategoricalData} instance
   */
  public static CategoricalData category(String label, double value) {
    return new CategoricalData(label, value);
  }

  /**
   * Creates a labeled data point with an explicit color override.
   *
   * @param label display label
   * @param value numeric value
   * @param color CSS color string (e.g. {@code "#E74C3C"})
   * @return a new {@link CategoricalData} instance
   */
  public static CategoricalData category(String label, double value, String color) {
    return new CategoricalData(label, value, color);
  }

  /**
   * Creates a labeled data point for line charts and area charts.
   *
   * @param label x-axis label (e.g. "Jan", "Week 1")
   * @param value y-axis value
   * @return a new {@link LineChart.DataPoint}
   */
  public static LineChart.DataPoint point(String label, double value) {
    return new LineChart.DataPoint(label, value);
  }

  /**
   * Creates a timestamped data point for time-series charts.
   *
   * @param timestamp the point in time
   * @param value measured value at that instant
   * @return a new {@link TimePoint}
   */
  public static TimePoint timePoint(Instant timestamp, double value) {
    return new TimePoint(timestamp, value);
  }

  /**
   * Creates an (x, y) coordinate for scatter plots.
   *
   * @param x horizontal value
   * @param y vertical value
   * @return a new {@link ScatterPlot.Point}
   */
  public static ScatterPlot.Point xy(double x, double y) {
    return new ScatterPlot.Point(x, y);
  }

  /**
   * Creates a bubble for bubble charts.
   *
   * @param label display label
   * @param x horizontal position
   * @param y vertical position
   * @param size bubble size (area)
   * @return a new {@link BubbleChart.Bubble}
   */
  public static BubbleChart.Bubble bubble(String label, double x, double y, double size) {
    return new BubbleChart.Bubble(label, x, y, size);
  }

  /**
   * Creates a bubble with an explicit color override.
   *
   * @param label display label
   * @param x horizontal position
   * @param y vertical position
   * @param size bubble size (area)
   * @param color CSS color string
   * @return a new {@link BubbleChart.Bubble}
   */
  public static BubbleChart.Bubble bubble(
      String label, double x, double y, double size, String color) {
    return new BubbleChart.Bubble(label, x, y, size, color);
  }

  /**
   * Creates a funnel stage.
   *
   * @param name stage name (e.g. "Visitors", "Sign-ups", "Purchases")
   * @param value numeric value at this stage
   * @return a new {@link FunnelChart.Stage}
   */
  public static FunnelChart.Stage stage(String name, double value) {
    return new FunnelChart.Stage(name, value);
  }

  /**
   * Creates a funnel stage with an explicit color.
   *
   * @param name stage name
   * @param value numeric value
   * @param color CSS color string
   * @return a new {@link FunnelChart.Stage}
   */
  public static FunnelChart.Stage stage(String name, double value, String color) {
    return new FunnelChart.Stage(name, value, color);
  }

  /**
   * Creates a gauge threshold band.
   *
   * @param label display label (e.g. "Low", "Normal", "Critical")
   * @param upTo upper bound of this band (inclusive)
   * @param color CSS color string for the band
   * @return a new {@link GaugeChart.Threshold}
   */
  public static GaugeChart.Threshold threshold(String label, double upTo, String color) {
    return new GaugeChart.Threshold(label, upTo, color);
  }

  // ── Series factories ────────────────────────────────────────────────

  /**
   * Creates a named data series for a line chart.
   *
   * @param name series name shown in the legend
   * @param points ordered data points
   * @return a new {@link LineChart.LineSeries}
   */
  public static LineChart.LineSeries lineSeries(String name, List<LineChart.DataPoint> points) {
    return new LineChart.LineSeries(name, points);
  }

  /**
   * Creates a named data series for a line chart with an explicit color.
   *
   * @param name series name
   * @param points ordered data points
   * @param color CSS color string
   * @return a new {@link LineChart.LineSeries}
   */
  public static LineChart.LineSeries lineSeries(
      String name, List<LineChart.DataPoint> points, String color) {
    return new LineChart.LineSeries(name, points, color);
  }

  /**
   * Creates a named data series for an area chart.
   *
   * @param name series name shown in the legend
   * @param points ordered data points
   * @return a new {@link AreaChart.AreaSeries}
   */
  public static AreaChart.AreaSeries areaSeries(String name, List<LineChart.DataPoint> points) {
    return new AreaChart.AreaSeries(name, points);
  }

  /**
   * Creates a named data series for an area chart with an explicit color.
   *
   * @param name series name
   * @param points ordered data points
   * @param color CSS color string
   * @return a new {@link AreaChart.AreaSeries}
   */
  public static AreaChart.AreaSeries areaSeries(
      String name, List<LineChart.DataPoint> points, String color) {
    return new AreaChart.AreaSeries(name, points, color);
  }

  /**
   * Creates a named data series for a scatter plot.
   *
   * @param name series name shown in the legend
   * @param points (x, y) coordinates
   * @return a new {@link ScatterPlot.ScatterSeries}
   */
  public static ScatterPlot.ScatterSeries scatterSeries(
      String name, List<ScatterPlot.Point> points) {
    return new ScatterPlot.ScatterSeries(name, points);
  }

  /**
   * Creates a named data series for a scatter plot with an explicit color.
   *
   * @param name series name
   * @param points (x, y) coordinates
   * @param color CSS color string
   * @return a new {@link ScatterPlot.ScatterSeries}
   */
  public static ScatterPlot.ScatterSeries scatterSeries(
      String name, List<ScatterPlot.Point> points, String color) {
    return new ScatterPlot.ScatterSeries(name, points, color);
  }

  /**
   * Creates a named data series for a stacked bar chart.
   *
   * @param name series name shown in the legend
   * @param values one value per category
   * @return a new {@link StackedBarChart.Series}
   */
  public static StackedBarChart.Series stackedSeries(String name, List<Double> values) {
    return new StackedBarChart.Series(name, values);
  }

  /**
   * Creates a named data series for a stacked bar chart with an explicit color.
   *
   * @param name series name
   * @param values one value per category
   * @param color CSS color string
   * @return a new {@link StackedBarChart.Series}
   */
  public static StackedBarChart.Series stackedSeries(
      String name, List<Double> values, String color) {
    return new StackedBarChart.Series(name, values, color);
  }

  /**
   * Creates a named data series for a radar chart.
   *
   * @param name series name shown in the legend
   * @param values one value per axis
   * @return a new {@link RadarChart.RadarSeries}
   */
  public static RadarChart.RadarSeries radarSeries(String name, List<Double> values) {
    return new RadarChart.RadarSeries(name, values);
  }

  /**
   * Creates a named data series for a radar chart with an explicit color.
   *
   * @param name series name
   * @param values one value per axis
   * @param color CSS color string
   * @return a new {@link RadarChart.RadarSeries}
   */
  public static RadarChart.RadarSeries radarSeries(String name, List<Double> values, String color) {
    return new RadarChart.RadarSeries(name, values, color);
  }

  // ── Chart factories ─────────────────────────────────────────────────

  /**
   * Creates a bar chart with a default y-axis label of "Value".
   *
   * @param title chart title
   * @param xAxis x-axis label
   * @param bars categorical data bars
   * @return a new {@link BarChart}
   */
  public static BarChart barChart(String title, String xAxis, List<CategoricalData> bars) {
    return new BarChart(title, xAxis, bars);
  }

  /**
   * Creates a bar chart with explicit axis labels.
   *
   * @param title chart title
   * @param xAxis x-axis label
   * @param yAxis y-axis label
   * @param bars categorical data bars
   * @return a new {@link BarChart}
   */
  public static BarChart barChart(
      String title, String xAxis, String yAxis, List<CategoricalData> bars) {
    return new BarChart(title, xAxis, yAxis, bars);
  }

  /**
   * Creates a pie chart.
   *
   * @param title chart title
   * @param segments pie segments
   * @return a new {@link PieChart}
   */
  public static PieChart pieChart(String title, List<CategoricalData> segments) {
    return new PieChart(title, segments);
  }

  /**
   * Creates a donut chart with no center text.
   *
   * @param title chart title
   * @param segments donut segments
   * @return a new {@link DonutChart}
   */
  public static DonutChart donutChart(String title, List<CategoricalData> segments) {
    return new DonutChart(title, segments);
  }

  /**
   * Creates a donut chart with center label and value.
   *
   * @param title chart title
   * @param centerLabel label shown in the center (e.g. "Total")
   * @param centerValue value shown in the center (e.g. "$1.2M")
   * @param segments donut segments
   * @return a new {@link DonutChart}
   */
  public static DonutChart donutChart(
      String title, String centerLabel, String centerValue, List<CategoricalData> segments) {
    return new DonutChart(title, centerLabel, centerValue, segments);
  }

  /**
   * Creates a line chart.
   *
   * @param title chart title
   * @param xAxis x-axis label
   * @param yAxis y-axis label
   * @param series one or more named line series
   * @return a new {@link LineChart}
   */
  public static LineChart lineChart(
      String title, String xAxis, String yAxis, List<LineChart.LineSeries> series) {
    return new LineChart(title, xAxis, yAxis, series);
  }

  /**
   * Creates a non-stacked area chart.
   *
   * @param title chart title
   * @param xAxis x-axis label
   * @param yAxis y-axis label
   * @param series one or more named area series
   * @return a new {@link AreaChart}
   */
  public static AreaChart areaChart(
      String title, String xAxis, String yAxis, List<AreaChart.AreaSeries> series) {
    return new AreaChart(title, xAxis, yAxis, series);
  }

  /**
   * Creates an area chart with explicit stacking control.
   *
   * @param title chart title
   * @param xAxis x-axis label
   * @param yAxis y-axis label
   * @param stacked {@code true} to stack series on top of each other
   * @param series one or more named area series
   * @return a new {@link AreaChart}
   */
  public static AreaChart areaChart(
      String title,
      String xAxis,
      String yAxis,
      boolean stacked,
      List<AreaChart.AreaSeries> series) {
    return new AreaChart(title, xAxis, yAxis, stacked, series);
  }

  /**
   * Creates a stacked bar chart.
   *
   * @param title chart title
   * @param xAxis x-axis label
   * @param yAxis y-axis label
   * @param categories category labels along the x-axis
   * @param series one or more named value series
   * @return a new {@link StackedBarChart}
   */
  public static StackedBarChart stackedBarChart(
      String title,
      String xAxis,
      String yAxis,
      List<String> categories,
      List<StackedBarChart.Series> series) {
    return new StackedBarChart(title, xAxis, yAxis, categories, series);
  }

  /**
   * Creates a scatter plot.
   *
   * @param title chart title
   * @param xAxis x-axis label
   * @param yAxis y-axis label
   * @param series one or more named point series
   * @return a new {@link ScatterPlot}
   */
  public static ScatterPlot scatterPlot(
      String title, String xAxis, String yAxis, List<ScatterPlot.ScatterSeries> series) {
    return new ScatterPlot(title, xAxis, yAxis, series);
  }

  /**
   * Creates a bubble chart.
   *
   * @param title chart title
   * @param xAxis x-axis label
   * @param yAxis y-axis label
   * @param sizeLabel label for the size dimension (e.g. "Population")
   * @param bubbles bubble data points
   * @return a new {@link BubbleChart}
   */
  public static BubbleChart bubbleChart(
      String title,
      String xAxis,
      String yAxis,
      String sizeLabel,
      List<BubbleChart.Bubble> bubbles) {
    return new BubbleChart(title, xAxis, yAxis, sizeLabel, bubbles);
  }

  /**
   * Creates a radar (spider/web) chart.
   *
   * @param title chart title
   * @param axes axis names (one per spoke)
   * @param series one or more named value series
   * @return a new {@link RadarChart}
   */
  public static RadarChart radarChart(
      String title, List<String> axes, List<RadarChart.RadarSeries> series) {
    return new RadarChart(title, axes, series);
  }

  /**
   * Creates a funnel chart.
   *
   * @param title chart title
   * @param stages ordered funnel stages from widest to narrowest
   * @return a new {@link FunnelChart}
   */
  public static FunnelChart funnelChart(String title, List<FunnelChart.Stage> stages) {
    return new FunnelChart(title, stages);
  }

  /**
   * Creates a gauge chart with no unit or thresholds.
   *
   * @param title chart title
   * @param value current value
   * @param min minimum scale value
   * @param max maximum scale value
   * @return a new {@link GaugeChart}
   */
  public static GaugeChart gaugeChart(String title, double value, double min, double max) {
    return new GaugeChart(title, value, min, max);
  }

  /**
   * Creates a gauge chart with a unit label and colored threshold bands.
   *
   * @param title chart title
   * @param value current value
   * @param min minimum scale value
   * @param max maximum scale value
   * @param unit unit label (e.g. "rpm", "%")
   * @param thresholds colored bands from low to high
   * @return a new {@link GaugeChart}
   */
  public static GaugeChart gaugeChart(
      String title,
      double value,
      double min,
      double max,
      String unit,
      List<GaugeChart.Threshold> thresholds) {
    return new GaugeChart(title, value, min, max, unit, thresholds);
  }

  /**
   * Creates a time-series chart.
   *
   * @param title chart title
   * @param granularity time bucketing (hourly, daily, etc.)
   * @param yAxis y-axis label
   * @param data timestamped data points
   * @return a new {@link TimeSeriesChart}
   */
  public static TimeSeriesChart timeSeriesChart(
      String title, TimeGranularity granularity, String yAxis, List<TimePoint> data) {
    return new TimeSeriesChart(title, granularity, yAxis, data);
  }

  /**
   * Creates a heat map without explicit category labels.
   *
   * @param title chart title
   * @param xLabel x-axis label
   * @param yLabel y-axis label
   * @param matrix intensity values as a 2D array {@code [row][col]}
   * @return a new {@link HeatMap}
   */
  public static HeatMap heatMap(String title, String xLabel, String yLabel, double[][] matrix) {
    return new HeatMap(title, xLabel, yLabel, matrix);
  }

  /**
   * Creates a heat map with labeled axes.
   *
   * @param title chart title
   * @param xLabel x-axis label
   * @param yLabel y-axis label
   * @param xCategories column category labels
   * @param yCategories row category labels
   * @param matrix intensity values as a 2D array {@code [row][col]}
   * @return a new {@link HeatMap}
   */
  public static HeatMap heatMap(
      String title,
      String xLabel,
      String yLabel,
      List<String> xCategories,
      List<String> yCategories,
      double[][] matrix) {
    return new HeatMap(title, xLabel, yLabel, xCategories, yCategories, matrix);
  }

  /**
   * Creates a KPI card.
   *
   * @param title card title (e.g. "Monthly Revenue")
   * @param mainValue primary displayed value (e.g. "$1.2M")
   * @param subValue secondary text (e.g. "+12% vs last month")
   * @param positiveTrend {@code true} for positive/green trend indicator
   * @return a new {@link KpiCard}
   */
  public static KpiCard kpiCard(
      String title, String mainValue, String subValue, boolean positiveTrend) {
    return new KpiCard(title, mainValue, subValue, positiveTrend);
  }

  /**
   * Creates a data table.
   *
   * @param title table title
   * @param headers column header labels
   * @param rows table rows, each a list of cell values
   * @return a new {@link DataTable}
   */
  public static DataTable dataTable(String title, List<String> headers, List<List<String>> rows) {
    return new DataTable(title, headers, rows);
  }

  /**
   * Creates a data table with a summary/total row.
   *
   * @param title table title
   * @param headers column headers
   * @param rows table rows, each a list of cell values
   * @param totalRow summary row rendered with bold styling
   * @return a new {@link DataTable}
   */
  public static DataTable dataTable(
      String title, List<String> headers, List<List<String>> rows, List<String> totalRow) {
    return new DataTable(title, headers, rows, totalRow);
  }

  // ── Layout ──────────────────────────────────────────────────────────

  /**
   * Creates a grid position with a span of 1x1.
   *
   * @param row zero-based row index
   * @param column zero-based column index
   * @return a new {@link LayoutPosition}
   */
  public static LayoutPosition position(int row, int column) {
    return new LayoutPosition(row, column);
  }

  /**
   * Creates a grid position with explicit row and column spans.
   *
   * @param row zero-based row index
   * @param column zero-based column index
   * @param rowSpan number of rows this cell spans
   * @param colSpan number of columns this cell spans
   * @return a new {@link LayoutPosition}
   */
  public static LayoutPosition position(int row, int column, int rowSpan, int colSpan) {
    return new LayoutPosition(row, column, rowSpan, colSpan);
  }

  /**
   * Creates a grid layout with the given number of columns and a default 16px gap.
   *
   * @param columns number of grid columns
   * @return a new {@link DashboardLayout}
   */
  public static DashboardLayout grid(int columns) {
    return new DashboardLayout(LayoutType.GRID, columns);
  }

  /**
   * Creates a grid layout with the given number of columns and gap.
   *
   * @param columns number of grid columns
   * @param gapPx spacing between panels in pixels
   * @return a new {@link DashboardLayout}
   */
  public static DashboardLayout grid(int columns, int gapPx) {
    return new DashboardLayout(LayoutType.GRID, columns, gapPx);
  }

  /** Creates a single-column layout. */
  public static DashboardLayout singleColumn() {
    return new DashboardLayout(LayoutType.SINGLE_COLUMN);
  }

  /** Creates a two-column layout. */
  public static DashboardLayout twoColumn() {
    return new DashboardLayout(LayoutType.TWO_COLUMN);
  }

  /** Creates a three-column layout. */
  public static DashboardLayout threeColumn() {
    return new DashboardLayout(LayoutType.THREE_COLUMN);
  }

  /** Creates a freeform (absolute-positioned) layout with a 12-column grid. */
  public static DashboardLayout freeform() {
    return new DashboardLayout(LayoutType.FREEFORM);
  }

  // ── Panels ──────────────────────────────────────────────────────────

  /**
   * Creates a panel placing a visualization at a 1x1 grid position.
   *
   * @param id unique panel identifier
   * @param viz the visualization to render
   * @param row zero-based row
   * @param column zero-based column
   * @return a new {@link Panel}
   */
  public static Panel panel(String id, Visualization viz, int row, int column) {
    return new Panel(id, viz, new LayoutPosition(row, column));
  }

  /**
   * Creates a panel placing a visualization at a grid position with spans.
   *
   * @param id unique panel identifier
   * @param viz the visualization to render
   * @param row zero-based row
   * @param column zero-based column
   * @param rowSpan number of rows to span
   * @param colSpan number of columns to span
   * @return a new {@link Panel}
   */
  public static Panel panel(
      String id, Visualization viz, int row, int column, int rowSpan, int colSpan) {
    return new Panel(id, viz, new LayoutPosition(row, column, rowSpan, colSpan));
  }

  /**
   * Creates a panel with an explicit {@link LayoutPosition}.
   *
   * @param id unique panel identifier
   * @param viz the visualization to render
   * @param position grid position
   * @return a new {@link Panel}
   */
  public static Panel panel(String id, Visualization viz, LayoutPosition position) {
    return new Panel(id, viz, position);
  }

  /**
   * Creates a panel with an auto-generated UUID as its identifier.
   *
   * @param viz the visualization to render
   * @param row zero-based row
   * @param column zero-based column
   * @return a new {@link Panel}
   */
  public static Panel panel(Visualization viz, int row, int column) {
    return new Panel(UUID.randomUUID().toString(), viz, new LayoutPosition(row, column));
  }

  // ── Data sources ────────────────────────────────────────────────────

  /**
   * Creates a data source descriptor.
   *
   * @param name human-readable name (e.g. "Sales DB")
   * @param type the kind of source
   * @param uri connection string, URL, or file path
   * @return a new {@link DataSource}
   */
  public static DataSource dataSource(String name, DataSourceType type, String uri) {
    return new DataSource(name, type, uri);
  }

  /**
   * Creates a data source descriptor with a query.
   *
   * @param name human-readable name
   * @param type the kind of source
   * @param uri connection string, URL, or file path
   * @param query query or endpoint used to fetch data
   * @return a new {@link DataSource}
   */
  public static DataSource dataSource(String name, DataSourceType type, String uri, String query) {
    return new DataSource(name, type, uri, query);
  }

  // ── Containers ──────────────────────────────────────────────────────

  /**
   * Creates a dashboard with the default light theme.
   *
   * @param id unique dashboard identifier
   * @param title display title
   * @param panels ordered list of panels
   * @param layout dashboard layout configuration
   * @return a new {@link Dashboard}
   */
  public static Dashboard dashboard(
      String id, String title, List<Panel> panels, DashboardLayout layout) {
    return new Dashboard(id, title, panels, layout);
  }

  /**
   * Creates a dashboard with a specific theme.
   *
   * @param id unique dashboard identifier
   * @param title display title
   * @param panels ordered list of panels
   * @param layout dashboard layout configuration
   * @param theme visual theme
   * @return a new {@link Dashboard}
   */
  public static Dashboard dashboard(
      String id, String title, List<Panel> panels, DashboardLayout layout, Theme theme) {
    return new Dashboard(id, title, panels, layout, theme);
  }

  /**
   * Creates a report with the default light theme.
   *
   * @param id unique report identifier
   * @param title report title
   * @param author who generated the report
   * @param sections ordered content sections
   * @return a new {@link Report}
   */
  public static Report report(
      String id, String title, String author, List<ReportSection> sections) {
    return new Report(id, title, author, sections);
  }

  /**
   * Creates a report with a description and specific theme.
   *
   * @param id unique report identifier
   * @param title report title
   * @param author who generated the report
   * @param description executive summary or subtitle
   * @param sections ordered content sections
   * @param theme visual theme
   * @return a new {@link Report}
   */
  public static Report report(
      String id,
      String title,
      String author,
      String description,
      List<ReportSection> sections,
      Theme theme) {
    return new Report(
        id, title, author, description, sections, theme, Map.of(), Instant.now(), null);
  }

  /**
   * Creates a report with a description, theme, and document style for PDF/HTML export.
   *
   * @param id unique report identifier
   * @param title report title
   * @param author who generated the report
   * @param description executive summary or subtitle
   * @param sections ordered content sections
   * @param theme visual theme
   * @param documentStyle PDF/HTML export styling
   * @return a new {@link Report}
   */
  public static Report report(
      String id,
      String title,
      String author,
      String description,
      List<ReportSection> sections,
      Theme theme,
      DocumentStyle documentStyle) {
    return new Report(id, title, author, description, sections, theme, documentStyle);
  }

  /**
   * Creates a report section with visualizations and no narrative.
   *
   * @param heading section title
   * @param visualizations charts and tables in this section
   * @return a new {@link ReportSection}
   */
  public static ReportSection section(String heading, List<Visualization> visualizations) {
    return new ReportSection(heading, visualizations);
  }

  /**
   * Creates a narrative-only report section with no visualizations.
   *
   * @param heading section title
   * @param narrative explanatory text (supports markdown)
   * @return a new {@link ReportSection}
   */
  public static ReportSection section(String heading, String narrative) {
    return new ReportSection(heading, narrative);
  }

  /**
   * Creates a report section with both narrative and visualizations.
   *
   * @param heading section title
   * @param narrative explanatory text (supports markdown)
   * @param visualizations charts and tables in this section
   * @return a new {@link ReportSection}
   */
  public static ReportSection section(
      String heading, String narrative, List<Visualization> visualizations) {
    return new ReportSection(heading, narrative, visualizations);
  }

  // ── Company profile ─────────────────────────────────────────────

  /**
   * Creates a company profile with name and logo.
   *
   * @param name legal entity name
   * @param logoUrl full logo URL for cover page (recommended: 200x60px, SVG/PNG)
   * @return a new {@link CompanyProfile}
   */
  public static CompanyProfile companyProfile(String name, String logoUrl) {
    return new CompanyProfile(name, logoUrl);
  }

  /**
   * Creates a company profile with name, logo, and header icon.
   *
   * @param name legal entity name
   * @param logoUrl full logo URL for cover page (recommended: 200x60px, SVG/PNG)
   * @param iconUrl small icon for running page header (recommended: 24x24px, SVG/PNG)
   * @return a new {@link CompanyProfile}
   */
  public static CompanyProfile companyProfile(String name, String logoUrl, String iconUrl) {
    return new CompanyProfile(name, logoUrl, iconUrl);
  }

  // ── Document style ────────────────────────────────────────────────

  /**
   * Creates a full document style with company profile, cover photo, header, footer, and page
   * config.
   *
   * @param profile company branding and identification
   * @param coverPhoto hero image for the cover page
   * @param header page header configuration
   * @param footer page footer configuration
   * @param page page size, orientation, and margins
   * @return a new {@link DocumentStyle}
   */
  public static DocumentStyle documentStyle(
      CompanyProfile profile,
      CoverPhoto coverPhoto,
      Header header,
      Footer footer,
      PageConfig page) {
    return new DocumentStyle(profile, coverPhoto, header, footer, page);
  }

  /**
   * Creates a document style with company profile and cover photo, using default page config.
   *
   * @param profile company branding and identification
   * @param coverPhoto hero image for the cover page
   * @param header page header configuration
   * @param footer page footer configuration
   * @return a new {@link DocumentStyle}
   */
  public static DocumentStyle documentStyle(
      CompanyProfile profile, CoverPhoto coverPhoto, Header header, Footer footer) {
    return new DocumentStyle(profile, coverPhoto, header, footer);
  }

  /**
   * Creates a document style with header, footer, and page config (no profile or cover).
   *
   * @param header page header with logo and company branding
   * @param footer page footer with text zones and page numbering
   * @param page page size, orientation, and margins
   * @return a new {@link DocumentStyle}
   */
  public static DocumentStyle documentStyle(Header header, Footer footer, PageConfig page) {
    return new DocumentStyle(header, footer, page);
  }

  /**
   * Creates a document style with only a header, using default footer and page config.
   *
   * @param header page header
   * @return a new {@link DocumentStyle}
   */
  public static DocumentStyle documentStyle(Header header) {
    return new DocumentStyle(header);
  }

  /**
   * Creates a cover photo for the report cover page.
   *
   * <p>Standard size: full page width, max height 180px.
   *
   * @param imageUrl path or URL to the cover image
   * @param caption caption or alt text
   * @return a new {@link CoverPhoto}
   */
  public static CoverPhoto coverPhoto(String imageUrl, String caption) {
    return new CoverPhoto(imageUrl, caption);
  }

  /**
   * Creates a cover photo with no caption.
   *
   * @param imageUrl path or URL to the cover image
   * @return a new {@link CoverPhoto}
   */
  public static CoverPhoto coverPhoto(String imageUrl) {
    return new CoverPhoto(imageUrl);
  }

  /**
   * Creates a page header with logo, company name, and subtitle.
   *
   * @param logoUrl path or URL to a logo image
   * @param companyName organization name
   * @param subtitle tagline or classification
   * @return a new {@link Header}
   */
  public static Header header(String logoUrl, String companyName, String subtitle) {
    return new Header(logoUrl, companyName, subtitle);
  }

  /**
   * Creates a page header with just a logo.
   *
   * @param logoUrl path or URL to a logo image
   * @return a new {@link Header}
   */
  public static Header header(String logoUrl) {
    return new Header(logoUrl);
  }

  /**
   * Creates a page footer with three text zones and page numbering control.
   *
   * @param left text on the left side (nullable)
   * @param center text in the center (nullable)
   * @param right text on the right side (nullable)
   * @param showPageNumbers {@code true} to render "Page X of Y"
   * @return a new {@link Footer}
   */
  public static Footer footer(String left, String center, String right, boolean showPageNumbers) {
    return new Footer(left, center, right, showPageNumbers);
  }

  /**
   * Creates a page footer with center text and page numbers enabled.
   *
   * @param centerText text shown in the center
   * @return a new {@link Footer}
   */
  public static Footer footer(String centerText) {
    return new Footer(centerText);
  }

  /**
   * Creates a page configuration.
   *
   * @param size paper size
   * @param orientation portrait or landscape
   * @param margins page margins
   * @return a new {@link PageConfig}
   */
  public static PageConfig pageConfig(PageSize size, Orientation orientation, Margins margins) {
    return new PageConfig(size, orientation, margins);
  }

  /**
   * Creates a page configuration with default 1-inch margins.
   *
   * @param size paper size
   * @param orientation portrait or landscape
   * @return a new {@link PageConfig}
   */
  public static PageConfig pageConfig(PageSize size, Orientation orientation) {
    return new PageConfig(size, orientation);
  }

  /**
   * Creates page margins with individual values.
   *
   * @param top top margin in mm
   * @param right right margin in mm
   * @param bottom bottom margin in mm
   * @param left left margin in mm
   * @return a new {@link Margins}
   */
  public static Margins margins(double top, double right, double bottom, double left) {
    return new Margins(top, right, bottom, left);
  }

  /**
   * Creates uniform page margins.
   *
   * @param allMm margin on all sides in mm
   * @return a new {@link Margins}
   */
  public static Margins margins(double allMm) {
    return new Margins(allMm);
  }

  // ── Theme ───────────────────────────────────────────────────────────

  /**
   * Returns the built-in light theme.
   *
   * @return {@link Theme#LIGHT}
   */
  public static Theme lightTheme() {
    return Theme.LIGHT;
  }

  /**
   * Returns the built-in dark theme.
   *
   * @return {@link Theme#DARK}
   */
  public static Theme darkTheme() {
    return Theme.DARK;
  }

  /**
   * Creates a custom theme.
   *
   * @param name theme identifier
   * @param palette color palette for data series
   * @param backgroundColor dashboard background color
   * @param textColor primary text color
   * @param titleFont font for chart titles
   * @param bodyFont font for labels and body text
   * @param borderRadius corner rounding in pixels
   * @return a new {@link Theme} instance
   */
  public static Theme theme(
      String name,
      ColorPalette palette,
      Color backgroundColor,
      Color textColor,
      FontSpec titleFont,
      FontSpec bodyFont,
      int borderRadius) {
    return new DefaultTheme(
        name, palette, backgroundColor, textColor, titleFont, bodyFont, borderRadius);
  }

  /**
   * Creates a custom color palette.
   *
   * @param name palette identifier (e.g. "corporate", "pastel")
   * @param colors ordered colors cycled through when assigning to data series
   * @return a new {@link ColorPalette} instance
   */
  public static ColorPalette colorPalette(String name, List<Color> colors) {
    return new DefaultColorPalette(name, colors);
  }

  /**
   * Creates a color from a CSS hex string.
   *
   * @param hex color in {@code "#RRGGBB"} or {@code "#RRGGBBAA"} format
   * @return a new {@link Color}
   */
  public static Color color(String hex) {
    return new Color(hex);
  }

  /**
   * Creates a custom font specification.
   *
   * @param family font family name (e.g. "Inter", "Roboto", "sans-serif")
   * @param sizePt font size in points
   * @param weight CSS-style weight (400 = normal, 700 = bold)
   * @return a new {@link FontSpec} instance
   */
  public static FontSpec font(String family, double sizePt, int weight) {
    return new DefaultFontSpec(family, sizePt, weight);
  }

  // ── Default implementations (private) ───────────────────────────────

  private record DefaultFontSpec(String family, double sizePt, int weight) implements FontSpec {}

  private record DefaultColorPalette(String name, List<Color> colors) implements ColorPalette {}

  private record DefaultTheme(
      String name,
      ColorPalette palette,
      Color backgroundColor,
      Color textColor,
      FontSpec titleFont,
      FontSpec bodyFont,
      int borderRadius)
      implements Theme {}
}
