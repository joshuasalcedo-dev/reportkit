package io.joshuasalcedo.reportkit.theme;

import java.util.List;
import io.joshuasalcedo.reportkit.DataKitAPI;

/**
 * An ordered list of colors used to assign series/segment colors automatically.
 *
 * <p>Implement this interface to provide corporate palettes, accessible palettes,
 * gradient-generated palettes, or palettes loaded from external configuration.
 *
 * @see DataKitAPI#colorPalette(String, List)
 */
public interface ColorPalette {

  ColorPalette DEFAULT =
      DataKitAPI.colorPalette(
          "default",
          List.of(
              new Color("#3498DB"),
              new Color("#2ECC71"),
              new Color("#E74C3C"),
              new Color("#F1C40F"),
              new Color("#9B59B6"),
              new Color("#E67E22"),
              new Color("#1ABC9C"),
              new Color("#34495E"),
              new Color("#E91E63"),
              new Color("#00BCD4")));

  /** Palette identifier (e.g. "corporate", "pastel"). */
  String name();

  /** Ordered colors cycled through when assigning to data series. */
  List<Color> colors();

  /** Returns the color at the given index, cycling if index exceeds palette size. */
  default Color colorAt(int index) {
    return colors().get(index % colors().size());
  }
}
