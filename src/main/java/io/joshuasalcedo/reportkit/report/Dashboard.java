package io.joshuasalcedo.reportkit.report;

import java.time.Instant;
import java.util.List;
import io.joshuasalcedo.reportkit.layout.DashboardLayout;
import io.joshuasalcedo.reportkit.layout.Panel;
import io.joshuasalcedo.reportkit.theme.Theme;

/**
 * Top-level container that composes panels, layout, and theme into a renderable dashboard.
 *
 * @param id unique dashboard identifier
 * @param title display title
 * @param description optional summary
 * @param panels ordered list of visualization panels
 * @param layout how panels are arranged
 * @param theme visual theme applied to all panels
 * @param createdAt creation timestamp
 * @param updatedAt last modification timestamp
 */
public record Dashboard(
    String id,
    String title,
    String description,
    List<Panel> panels,
    DashboardLayout layout,
    Theme theme,
    Instant createdAt,
    Instant updatedAt) {

  public Dashboard(String id, String title, List<Panel> panels, DashboardLayout layout) {
    this(id, title, "", panels, layout, Theme.LIGHT, Instant.now(), Instant.now());
  }

  public Dashboard(
      String id, String title, List<Panel> panels, DashboardLayout layout, Theme theme) {
    this(id, title, "", panels, layout, theme, Instant.now(), Instant.now());
  }
}
