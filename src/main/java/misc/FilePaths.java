/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package misc;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

/**
 * A utility class for handling file paths.
 *
 * @author Jesse Stricker
 */
public final class FilePaths {
  private FilePaths() {
  }

  /**
   * Gets the filename without the extension from a given path, that is everything before the last '.'.
   * The '.' itself is not included.<br>
   * If the path is empty or the filename consists just of an extension, an empty string is returned.
   * <p>
   * Examples:
   * <pre>
   * getExtension(Path.of("foo/doc.txt")) -> "doc"
   * getExtension(Path.of(""))            -> ""
   * getExtension(Path.of("makefile"))    -> "makefile"
   * getExtension(Path.of(".gitignore"))  -> ""
   *
   * @param path the path to extract the filename without extension from
   * @return the filename or an empty string
   */
  @NotNull
  public static String getNameWithoutExt(@NotNull Path path) {
    var name = getFileName(path);
    var dotIndex = name.lastIndexOf('.');
    return (dotIndex != -1) ? name.substring(0, dotIndex) : name;
  }

  /**
   * Gets the filename extension from a given path, that is everything after the last '.'.
   * The '.' itself is not included.<br>
   * If the path is empty or the filename doesn't have an extension, an empty string is returned.
   * <p>
   * Examples:
   * <pre>
   * getExtension(Path.of("foo/doc.txt")) -> "txt"
   * getExtension(Path.of(""))            -> ""
   * getExtension(Path.of("makefile"))    -> ""
   * getExtension(Path.of(".gitignore"))  -> "gitignore"
   *
   * @param path the path to extract the filename extension from
   * @return the filename extension or an empty string
   */
  @NotNull
  public static String getExtension(@NotNull Path path) {
    var name = getFileName(path);
    var dotIndex = name.lastIndexOf('.');
    return (dotIndex != -1) ? name.substring(dotIndex + 1) : "";
  }

  /**
   * Returns a new path with the filename extension replaced or removed.
   *
   * @param path the path to change the filename extension of
   * @param ext  the new filename extension (without the '.'), or {@code null} to remove the filename extension
   * @return a new path with the given filename extension
   */
  @NotNull
  public static Path replaceExtension(@NotNull Path path, @NotNull String ext) {
    var name = getNameWithoutExt(path);
    if (!ext.equals("")) name += '.' + ext;
    return path.resolveSibling(name);
  }

  /**
   * Returns the file name of a path as a {@link String}.
   *
   * @param path the path to get the file name from
   * @return the file name or {@code ""} if the path is empty
   */
  @NotNull
  static String getFileName(@NotNull Path path) {
    var fileName = path.getFileName();
    return fileName == null ? "" : fileName.toString();
  }
}
