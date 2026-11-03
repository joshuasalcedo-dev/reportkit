package io.joshuasalcedo.reportkit.theme;

import io.joshuasalcedo.reportkit.DataKitAPI;

/**
 * Visual theme applied across all visualizations in a dashboard.
 *
 * <p>Implement this interface to provide corporate themes, computed accent colors, dynamic themes
 * that respond to context, or themes with extra properties.
 *
 * @see DataKitAPI#theme(String, ColorPalette, Color, Color, FontSpec, FontSpec, int)
 * @see DataKitAPI#lightTheme()
 * @see DataKitAPI#darkTheme()
 */
public interface Theme {

  Theme LIGHT =
      DataKitAPI.theme(
          "light",
          ColorPalette.DEFAULT,
          Color.WHITE,
          new Color("#2C3E50"),
          FontSpec.DEFAULT_TITLE,
          FontSpec.DEFAULT_BODY,
          8);

  Theme DARK =
      DataKitAPI.theme(
          "dark",
          ColorPalette.DEFAULT,
          new Color("#1A1A2E"),
          new Color("#EAEAEA"),
          FontSpec.DEFAULT_TITLE,
          FontSpec.DEFAULT_BODY,
          8);

  /** Theme identifier. */
  String name();

  /** Color palette for data series. */
  ColorPalette palette();

  /** Dashboard background color. */
  Color backgroundColor();

  /** Primary text color. */
  Color textColor();

  /** Font used for chart titles. */
  FontSpec titleFont();

  /** Font used for labels and body text. */
  FontSpec bodyFont();

  /** Corner rounding in pixels for panels. */
  int borderRadius();
}
