package io.joshuasalcedo.reportkit.datasource;

import java.time.Instant;
import java.util.Map;

/**
 * Metadata describing where a visualization's data comes from.
 *
 * @param name human-readable name (e.g. "Sales DB", "user-api")
 * @param type the kind of source
 * @param uri connection string, URL, or file path
 * @param query query/endpoint used to fetch data (nullable)
 * @param parameters key-value parameters passed to the query (nullable)
 * @param refreshedAt when the data was last fetched (nullable)
 */
public record DataSource(
    String name,
    DataSourceType type,
    String uri,
    String query,
    Map<String, String> parameters,
    Instant refreshedAt) {

  public DataSource(String name, DataSourceType type, String uri) {
    this(name, type, uri, null, Map.of(), null);
  }

  public DataSource(String name, DataSourceType type, String uri, String query) {
    this(name, type, uri, query, Map.of(), null);
  }
}
