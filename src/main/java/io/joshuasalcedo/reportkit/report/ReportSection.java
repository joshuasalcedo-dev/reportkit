package io.joshuasalcedo.reportkit.report;

import java.util.List;
import io.joshuasalcedo.reportkit.chart.Visualization;

/**
 * A section within a report, containing a heading, optional narrative text, and zero or more
 * visualizations displayed in order.
 *
 * @param heading section title
 * @param narrative explanatory text or commentary (supports markdown)
 * @param visualizations charts/tables rendered within this section
 */
public record ReportSection(String heading, String narrative, List<Visualization> visualizations) {

  public ReportSection(String heading, List<Visualization> visualizations) {
    this(heading, "", visualizations);
  }

  public ReportSection(String heading, String narrative) {
    this(heading, narrative, List.of());
  }
}
