package io.joshuasalcedo.reportkit.report;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import io.joshuasalcedo.reportkit.theme.Theme;

/**
 * A printable/exportable report composed of ordered sections. Unlike a Dashboard (interactive,
 * grid-based), a Report flows top-to-bottom and is designed for PDF/HTML export.
 *
 * @param id unique report identifier
 * @param title report title
 * @param author who generated the report
 * @param description executive summary or subtitle
 * @param sections ordered content sections
 * @param theme visual theme
 * @param metadata arbitrary key-value metadata (e.g. department, version)
 * @param createdAt creation timestamp
 * @param documentStyle PDF/HTML export styling (nullable for default rendering)
 */
public record Report(
    String id,
    String title,
    String author,
    String description,
    List<ReportSection> sections,
    Theme theme,
    Map<String, String> metadata,
    Instant createdAt,
    DocumentStyle documentStyle) {

  public Report(String id, String title, String author, List<ReportSection> sections) {
    this(id, title, author, "", sections, Theme.LIGHT, Map.of(), Instant.now(), null);
  }

  public Report(
      String id,
      String title,
      String author,
      String description,
      List<ReportSection> sections,
      Theme theme,
      Map<String, String> metadata,
      Instant createdAt) {
    this(id, title, author, description, sections, theme, metadata, createdAt, null);
  }

  public Report(
      String id,
      String title,
      String author,
      String description,
      List<ReportSection> sections,
      Theme theme,
      DocumentStyle documentStyle) {
    this(id, title, author, description, sections, theme, Map.of(), Instant.now(), documentStyle);
  }
}
