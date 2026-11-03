package io.joshuasalcedo.reportkit;

/**
 * Thrown when report export (HTML rendering or PDF generation) fails.
 *
 * @author JoshuaSalcedo
 */
public class ReportExportException extends RuntimeException {

  public ReportExportException(String message) {
    super(message);
  }

  public ReportExportException(String message, Throwable cause) {
    super(message, cause);
  }
}
