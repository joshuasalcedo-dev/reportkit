package io.joshuasalcedo.reportkit.examples;

import io.joshuasalcedo.reportkit.invoice.InvoiceDocument;
import io.joshuasalcedo.reportkit.invoice.InvoiceDocument.Detail;
import io.joshuasalcedo.reportkit.invoice.InvoiceDocument.Line;
import io.joshuasalcedo.reportkit.invoice.InvoiceDocument.Party;
import java.util.List;

/** An invoice. Every value is already formatted text, so the caller decides how amounts read. */
public final class InvoiceExample {

  private InvoiceExample() {}

  public static InvoiceDocument invoice() {
    return new InvoiceDocument(
        "Invoice",
        "INV-2026-0042",
        new Party(
            "Acme Corp", List.of("100 Market Street", "Springfield", "billing@acme.example")),
        new Party("Globex Ltd", List.of("7 Harbour Road", "Shelbyville", "ap@globex.example")),
        List.of(
            new Detail("Issued", "Nov 3, 2026"),
            new Detail("Due", "Dec 3, 2026"),
            new Detail("Terms", "Net 30")),
        List.of(
            new Line("Cordless drill", "4", "119.00", "476.00"),
            new Line("Tool chest", "1", "349.00", "349.00"),
            new Line("Socket set, 94 pieces", "6", "40.00", "240.00"),
            new Line("Delivery", "1", "35.00", "35.00")),
        List.of(
            new Detail("Subtotal", "1,100.00"),
            new Detail("Tax (8%)", "88.00"),
            new Detail("Amount due", "USD 1,188.00", true)),
        "",
        "Thank you for your business. Please quote the invoice number with your payment.");
  }
}
