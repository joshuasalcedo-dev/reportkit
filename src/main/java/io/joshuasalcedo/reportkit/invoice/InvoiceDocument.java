package io.joshuasalcedo.reportkit.invoice;

import java.util.List;
import java.util.Objects;

/**
 * An invoice, credit note or similar billing document, ready to print: who sends it, who it is for,
 * its details, its lines and what they add up to. Every value is already formatted text, as in a
 * {@link io.joshuasalcedo.reportkit.chart.DataTable}, so the caller decides how amounts and dates read.
 *
 * @param title what the document is, such as "Invoice" or "Credit note"
 * @param number its number
 * @param seller who sends it
 * @param billTo who it is for
 * @param details the facts beside the bill-to block, in order: issued, due, terms
 * @param lines what is billed
 * @param summary the totals under the lines, in order; the emphasized ones stand out
 * @param stamp a word across the top, such as "PAID" or "VOID", or empty
 * @param notes a note for the customer, or empty
 */
public record InvoiceDocument(
    String title,
    String number,
    Party seller,
    Party billTo,
    List<Detail> details,
    List<Line> lines,
    List<Detail> summary,
    String stamp,
    String notes) {

  /**
   * A business or person on the document.
   *
   * @param name its name
   * @param lines the rest, one per line: address, tax number, email
   */
  public record Party(String name, List<String> lines) {
    public Party {
      Objects.requireNonNull(name, "name");
      lines = List.copyOf(lines);
    }
  }

  /**
   * A labelled value.
   *
   * @param label what it is
   * @param value its value
   * @param emphasized whether it stands out, as the amount due does
   */
  public record Detail(String label, String value, boolean emphasized) {
    public Detail(String label, String value) {
      this(label, value, false);
    }
  }

  /**
   * One line.
   *
   * @param description what is billed
   * @param quantity how many
   * @param unitPrice the price of one
   * @param amount what the line comes to
   */
  public record Line(String description, String quantity, String unitPrice, String amount) {}

  /** Copies the lists; a missing stamp or note is empty. */
  public InvoiceDocument {
    Objects.requireNonNull(title, "title");
    Objects.requireNonNull(number, "number");
    Objects.requireNonNull(seller, "seller");
    Objects.requireNonNull(billTo, "billTo");
    details = List.copyOf(details);
    lines = List.copyOf(lines);
    summary = List.copyOf(summary);
    stamp = stamp == null ? "" : stamp;
    notes = notes == null ? "" : notes;
  }
}
