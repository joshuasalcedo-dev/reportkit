/**
 * Visualization types — a sealed hierarchy of charts, tables, and KPI cards.
 *
 * <p>All concrete types implement the sealed {@link io.joshuasalcedo.reportkit.chart.Visualization} interface,
 * enabling exhaustive {@code switch} expressions:
 *
 * <pre>{@code
 * switch (viz) {
 *     case BarChart b   -> renderBar(b);
 *     case PieChart p   -> renderPie(p);
 *     case KpiCard k    -> renderKpi(k);
 *     // ...
 * }
 * }</pre>
 *
 * <h2>Chart types by category</h2>
 *
 * <ul>
 *   <li><b>Proportion</b> — {@link io.joshuasalcedo.reportkit.chart.PieChart}, {@link
 *       io.joshuasalcedo.reportkit.chart.DonutChart}
 *   <li><b>Comparison</b> — {@link io.joshuasalcedo.reportkit.chart.BarChart}, {@link
 *       io.joshuasalcedo.reportkit.chart.StackedBarChart}, {@link io.joshuasalcedo.reportkit.chart.RadarChart}
 *   <li><b>Trend</b> — {@link io.joshuasalcedo.reportkit.chart.LineChart}, {@link
 *       io.joshuasalcedo.reportkit.chart.AreaChart}, {@link io.joshuasalcedo.reportkit.chart.TimeSeriesChart}
 *   <li><b>Correlation</b> — {@link io.joshuasalcedo.reportkit.chart.ScatterPlot}, {@link
 *       io.joshuasalcedo.reportkit.chart.BubbleChart}
 *   <li><b>Distribution</b> — {@link io.joshuasalcedo.reportkit.chart.HeatMap}
 *   <li><b>Flow</b> — {@link io.joshuasalcedo.reportkit.chart.FunnelChart}
 *   <li><b>KPI</b> — {@link io.joshuasalcedo.reportkit.chart.KpiCard}, {@link io.joshuasalcedo.reportkit.chart.GaugeChart}
 *   <li><b>Detail</b> — {@link io.joshuasalcedo.reportkit.chart.DataTable}
 * </ul>
 *
 * <p>Prefer the factory methods in {@link io.joshuasalcedo.reportkit.DataKitAPI} over direct constructor calls.
 *
 * @see io.joshuasalcedo.reportkit.chart.Visualization
 * @see io.joshuasalcedo.reportkit.chart.VisualizationType
 */
package io.joshuasalcedo.reportkit.chart;
