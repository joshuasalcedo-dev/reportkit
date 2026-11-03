package io.joshuasalcedo.reportkit.theme;

import io.joshuasalcedo.reportkit.DataKitAPI;

/**
 * Font specification for themed text rendering.
 *
 * <p>Implement this interface to provide custom font resolution logic (e.g. web font URLs,
 * platform-specific fallbacks).
 *
 * @see DataKitAPI#font(String, double, int)
 */
public interface FontSpec {

  FontSpec DEFAULT_BODY = DataKitAPI.font("sans-serif", 12, 400);
  FontSpec DEFAULT_TITLE = DataKitAPI.font("sans-serif", 16, 700);
  FontSpec DEFAULT_LABEL = DataKitAPI.font("sans-serif", 10, 400);

  /** Font family name (e.g. "Inter", "Roboto", "sans-serif"). */
  String family();

  /** Font size in points. */
  double sizePt();

  /** CSS-style weight (400 = normal, 700 = bold). */
  int weight();
}
