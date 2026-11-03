package io.joshuasalcedo.reportkit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * PDF class.
 *
 * @author JoshuaSalcedo
 * @since 3/11/2026 5:44 AM
 */
public record PDF(byte[] bytes) {

  public File toFile(Path path) throws IOException {
    Files.write(path, bytes);
    return path.toFile();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PDF(byte[] bytes1))) return false;
    return Arrays.equals(bytes, bytes1);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(bytes);
  }

  @Override
  public String toString() {
    return "PDF{" + bytes.length + " bytes}";
  }
}
