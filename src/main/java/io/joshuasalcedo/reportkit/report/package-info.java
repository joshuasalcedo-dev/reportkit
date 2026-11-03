/**
 * Top-level document containers: dashboards and reports.
 *
 * <ul>
 *   <li>{@link io.joshuasalcedo.reportkit.report.Dashboard} — interactive, grid-based visualization layout with
 *       panels and a theme.
 *   <li>{@link io.joshuasalcedo.reportkit.report.Report} — printable, top-to-bottom document composed of {@link
 *       io.joshuasalcedo.reportkit.report.ReportSection}s, designed for PDF/HTML export.
 *   <li>{@link io.joshuasalcedo.reportkit.report.DocumentStyle} — rendering configuration for PDF/HTML export:
 *       header branding, footer layout, and page geometry.
 *   <li>{@link io.joshuasalcedo.reportkit.report.CompanyProfile} — company branding, identification, and image
 *       size standards for financial reports.
 * </ul>
 *
 * @see io.joshuasalcedo.reportkit.DataKitAPI#dashboard
 * @see io.joshuasalcedo.reportkit.DataKitAPI#report
 * @see io.joshuasalcedo.reportkit.DataKitAPI#documentStyle
 */
package io.joshuasalcedo.reportkit.report;
