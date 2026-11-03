package io.joshuasalcedo.reportkit.examples;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.joshuasalcedo.reportkit.ReportExporter;
import io.joshuasalcedo.reportkit.ReportStyle;
import org.junit.jupiter.api.Test;

/** The examples in the README keep rendering. */
class ExamplesTest {

  final ReportExporter exporter = ReportExporter.createDefault();

  @Test
  void theSalesReportRendersAsAPdf() {
    var pdf = exporter.reportToPDF(SalesReportExample.report(), ReportStyle.MODERN);
    assertTrue(pdf.bytes().length > 0);
  }

  @Test
  void theChartGalleryRendersAsAPdf() {
    assertTrue(exporter.reportToPDF(ChartGalleryExample.report()).bytes().length > 0);
  }

  @Test
  void theInvoiceRendersAsAPdf() {
    var html = exporter.toHTML(InvoiceExample.invoice()).html();
    assertTrue(html.contains("INV-2026-0042"));
    assertTrue(exporter.invoiceToPDF(InvoiceExample.invoice()).bytes().length > 0);
  }
}
