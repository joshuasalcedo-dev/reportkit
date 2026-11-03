package io.joshuasalcedo.reportkit.chart;

public record CategoricalData(String label, double value, String color) {

  public CategoricalData(String label, double value) {
    this(label, value, null);
  }
}
