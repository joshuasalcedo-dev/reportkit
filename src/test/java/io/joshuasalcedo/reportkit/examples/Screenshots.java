package io.joshuasalcedo.reportkit.examples;

import io.joshuasalcedo.reportkit.PDF;
import io.joshuasalcedo.reportkit.ReportExporter;
import io.joshuasalcedo.reportkit.ReportStyle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * Renders every example as HTML and PDF into {@code target/examples}, and each PDF page as a PNG
 * into {@code docs/screenshots}.
 *
 * <pre>mvn test-compile exec:java@screenshots</pre>
 */
public final class Screenshots {

  private static final float DPI = 96;

  private Screenshots() {}

  public static void main(String[] args) throws IOException {
    Path examples = Path.of("target", "examples");
    Path screenshots = Path.of("docs", "screenshots");
    Files.createDirectories(examples);
    Files.createDirectories(screenshots);

    ReportExporter exporter = ReportExporter.createDefault();

    var sales = exporter.toHTML(SalesReportExample.report(), ReportStyle.MODERN);
    write(examples, screenshots, "sales-report", sales.html(), exporter.toPDF(sales));

    var gallery = exporter.toHTML(ChartGalleryExample.report());
    write(examples, screenshots, "chart-gallery", gallery.html(), exporter.toPDF(gallery));

    var invoice = exporter.toHTML(InvoiceExample.invoice());
    write(examples, screenshots, "invoice", invoice.html(), exporter.toPDF(invoice));
  }

  private static void write(Path examples, Path screenshots, String name, String html, PDF pdf)
      throws IOException {
    Files.writeString(examples.resolve(name + ".html"), html);
    Files.write(examples.resolve(name + ".pdf"), pdf.bytes());
    try (PDDocument document = Loader.loadPDF(pdf.bytes())) {
      PDFRenderer renderer = new PDFRenderer(document);
      for (int page = 0; page < document.getNumberOfPages(); page++) {
        BufferedImage image = renderer.renderImageWithDPI(page, DPI, ImageType.RGB);
        Path png = screenshots.resolve(name + "-" + (page + 1) + ".png");
        ImageIO.write(image, "png", png.toFile());
        System.out.println(png);
      }
    }
  }
}
