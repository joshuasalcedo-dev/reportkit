/**
 * Layout primitives for positioning visualizations within a dashboard.
 *
 * <p>A {@link io.joshuasalcedo.reportkit.layout.DashboardLayout} defines the grid structure (column count, gap),
 * while each {@link io.joshuasalcedo.reportkit.layout.Panel} binds a visualization to a {@link
 * io.joshuasalcedo.reportkit.layout.LayoutPosition} within that grid.
 *
 * <pre>{@code
 * import static io.joshuasalcedo.reportkit.DataKitAPI.*;
 *
 * var layout = grid(3);                        // 3-column grid, 16px gap
 * var p1 = panel("p1", chart, 0, 0);           // row 0, col 0
 * var p2 = panel("p2", table, 0, 1, 1, 2);     // row 0, col 1, spans 2 columns
 * }</pre>
 *
 * @see io.joshuasalcedo.reportkit.layout.DashboardLayout
 * @see io.joshuasalcedo.reportkit.layout.Panel
 * @see io.joshuasalcedo.reportkit.layout.LayoutPosition
 */
package io.joshuasalcedo.reportkit.layout;
