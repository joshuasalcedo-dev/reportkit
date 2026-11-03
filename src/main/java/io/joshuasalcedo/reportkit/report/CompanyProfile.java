package io.joshuasalcedo.reportkit.report;

/**
 * Company branding and identification for financial reports.
 *
 * <p>Following financial reporting standards (IFRS/GAAP), the cover page of any formal report
 * should identify the reporting entity. This record captures the standard fields found on annual
 * reports, 10-K filings, and audited statements.
 *
 * <pre>{@code
 * import static io.joshuasalcedo.reportkit.DataKitAPI.*;
 *
 * var profile = companyProfile("Acme Corp", "https://cdn.example.com/logo.svg")
 *     .withTagline("Innovative Solutions Since 2010")
 *     .withRegistration("SEC File No. 001-12345")
 *     .withAddress("123 Main Street, Suite 400, New York, NY 10001")
 *     .withContact("investor.relations@acme.com", "+1 (212) 555-0100", "https://acme.com");
 * }</pre>
 *
 * @param name legal entity name (e.g. "Acme Corporation")
 * @param logoUrl full logo for cover page (recommended: 200x60px, SVG/PNG)
 * @param iconUrl small icon for running header (recommended: 24x24px, SVG/PNG)
 * @param tagline company tagline or slogan (nullable)
 * @param registrationInfo regulatory registration (e.g. SEC file number, ABN, CRN) (nullable)
 * @param address registered office address (nullable)
 * @param email contact email (nullable)
 * @param phone contact phone (nullable)
 * @param website company website URL (nullable)
 */
public record CompanyProfile(
    String name,
    String logoUrl,
    String iconUrl,
    String tagline,
    String registrationInfo,
    String address,
    String email,
    String phone,
    String website) {

  /** Creates a profile with name and logo only. */
  public CompanyProfile(String name, String logoUrl) {
    this(name, logoUrl, null, null, null, null, null, null, null);
  }

  /** Creates a profile with name, logo, and icon. */
  public CompanyProfile(String name, String logoUrl, String iconUrl) {
    this(name, logoUrl, iconUrl, null, null, null, null, null, null);
  }

  // ── Fluent builders (return new record copies) ───────────────────

  public CompanyProfile withTagline(String tagline) {
    return new CompanyProfile(
        name, logoUrl, iconUrl, tagline, registrationInfo, address, email, phone, website);
  }

  public CompanyProfile withRegistration(String registrationInfo) {
    return new CompanyProfile(
        name, logoUrl, iconUrl, tagline, registrationInfo, address, email, phone, website);
  }

  public CompanyProfile withAddress(String address) {
    return new CompanyProfile(
        name, logoUrl, iconUrl, tagline, registrationInfo, address, email, phone, website);
  }

  public CompanyProfile withContact(String email, String phone, String website) {
    return new CompanyProfile(
        name, logoUrl, iconUrl, tagline, registrationInfo, address, email, phone, website);
  }

  public CompanyProfile withIcon(String iconUrl) {
    return new CompanyProfile(
        name, logoUrl, iconUrl, tagline, registrationInfo, address, email, phone, website);
  }

  // ── Image size standards (financial report conventions) ──────────

  /**
   * Standard image dimensions for financial report elements. Based on SEC/IFRS filing conventions
   * and print-safe DPI guidelines.
   */
  public static final class ImageSize {

    private ImageSize() {}

    /** Cover logo: 200px wide, auto height. Suitable for cover page branding. */
    public static final int COVER_LOGO_WIDTH_PX = 200;

    public static final int COVER_LOGO_MAX_HEIGHT_PX = 60;

    /** Header icon: 24x24px. Small mark for running page headers. */
    public static final int HEADER_ICON_SIZE_PX = 24;

    /** Cover photo: full page width, 180px tall. Hero image on report cover. */
    public static final int COVER_PHOTO_MAX_HEIGHT_PX = 180;

    /** Inline chart placeholder: 100% width, 240px max height. */
    public static final int CHART_IMAGE_MAX_HEIGHT_PX = 240;
  }
}
