package io.joshuasalcedo.reportkit.theme;

/**
 * An immutable color value stored as a CSS-compatible hex string.
 *
 * @param hex color in "#RRGGBB" or "#RRGGBBAA" format
 */
public record Color(String hex) {

  public static final Color WHITE = new Color("#FFFFFF");
  public static final Color BLACK = new Color("#000000");
  public static final Color RED = new Color("#E74C3C");
  public static final Color GREEN = new Color("#2ECC71");
  public static final Color BLUE = new Color("#3498DB");
  public static final Color YELLOW = new Color("#F1C40F");
  public static final Color ORANGE = new Color("#E67E22");
  public static final Color PURPLE = new Color("#9B59B6");
  public static final Color GRAY = new Color("#95A5A6");

  @Override
  public String toString() {
    return hex;
  }
}
