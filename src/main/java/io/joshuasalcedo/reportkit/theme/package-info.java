/**
 * Customizable theming system for visualizations and dashboards.
 *
 * <p>The core interfaces — {@link io.joshuasalcedo.reportkit.theme.Theme}, {@link
 * io.joshuasalcedo.reportkit.theme.ColorPalette}, and {@link io.joshuasalcedo.reportkit.theme.FontSpec} — can be implemented
 * by library consumers to provide corporate themes, accessible palettes, or platform-specific font
 * resolution.
 *
 * <p>Default implementations are private records inside {@link io.joshuasalcedo.reportkit.DataKitAPI} and are
 * created via its factory methods: {@link io.joshuasalcedo.reportkit.DataKitAPI#theme theme()}, {@link
 * io.joshuasalcedo.reportkit.DataKitAPI#colorPalette colorPalette()}, and {@link io.joshuasalcedo.reportkit.DataKitAPI#font
 * font()}. Built-in constants ({@code Theme.LIGHT}, {@code Theme.DARK}, {@code
 * ColorPalette.DEFAULT}, {@code FontSpec.DEFAULT_BODY}, etc.) are available directly on the
 * interfaces.
 *
 * <p>{@link io.joshuasalcedo.reportkit.theme.Color} is an immutable hex-string wrapper and stays a record —
 * create new instances rather than subclassing.
 *
 * @see io.joshuasalcedo.reportkit.theme.Theme
 * @see io.joshuasalcedo.reportkit.theme.ColorPalette
 * @see io.joshuasalcedo.reportkit.theme.FontSpec
 */
package io.joshuasalcedo.reportkit.theme;
