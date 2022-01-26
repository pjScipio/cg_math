/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package misc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * Computes the relative path to an asset - uses a platform specific implementation.
 *
 * @author Philipp Jenke
 */
public class AssetPath {
  private static final String[] ROOT_DIRS = {
          "src/main/resources/",
          "cg_algorithms_datastructures/src/main/resources/",
          "pcg/src/main/resources/"};

  private static AssetPath instance;

  private AssetPath() {
  }

  public static AssetPath getInstance() {
    if (instance == null) {
      instance = new AssetPath();
    }
    return instance;
  }

  /**
   * Reads the full content of an asset file into a string.
   *
   * @param assetPath a file path relative to the asset path root
   * @return the contents of the file
   * @throws IllegalArgumentException if the given asset path does not exist
   * @throws IOException              if the file could not be read
   */
  public String readTextFileToString(String assetPath) throws IOException {
    var filePath = getPathToAsset(assetPath);
    if (filePath == null) {
      throw new IllegalArgumentException("No such asset file: " + assetPath);
    }
    return Files.readString(Path.of(filePath));
  }

  /**
   * Opens a stream to an asset file.
   *
   * @param assetPath a file path relative to the asset path root
   * @return the opened stream, unbuffered
   * @throws IllegalArgumentException if the given asset path does not exist
   * @throws IOException              if the file could not be opened
   */
  public Optional<InputStream> readTextFileToStream(String assetPath) {

    /*
    try {
      return Optional.of(Files.newInputStream(Path.of(assetPath)));
    } catch (IOException e) {
      return Optional.empty();
    }
    */

    var filePath = getPathToAsset(assetPath);
    if (filePath == null) {
      return Optional.empty();
    }
    try {
      return Optional.of(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      return Optional.empty();
    }
  }

  /**
   * Converts an asset path to a file path.
   *
   * @param assetPath a file path relative to the asset path root
   * @return a file path relative to the project directory, or {@code null} if no such asset file exists
   */
  public String getPathToAsset(String assetPath) {
    for (var rootDir : ROOT_DIRS) {
      var filePath = new File(rootDir + assetPath);
      String absPath = new File(rootDir + assetPath).getAbsolutePath();
      if (filePath.exists()) {
        return filePath.toString();
      }
    }
    return null;
  }

  /**
   * Returns a list of all files in the given directory.
   *
   * @param fileExt the extension of the file without the dot ({@code '.'}).
   * @return the list of asset paths inside the dir (relative to the asset path root),
   * or an empty list if there was any error
   */
  public List<String> getFilesInDir(String dir, String fileExt) {
    return getFilesInDirRecursive(dir, fileExt, 1);
  }

  /**
   * Returns a list of all files in the given directory or its subdirectories.
   *
   * @param fileExt the extension of the file without the dot ({@code '.'}).
   * @return the list of asset paths inside the dir (relative to the asset path root),
   * or an empty list if there was any error
   */
  public List<String> getFilesInDirRecursive(String dir, String fileExt) {
    return getFilesInDirRecursive(dir, fileExt, Integer.MAX_VALUE);
  }

  /**
   * Returns a list of all files in the given directory or its subdirectories
   * at a maximum specified depth.
   *
   * @param fileExt the extension of the file without the dot ({@code '.'}).
   * @return the list of asset paths inside the dir (relative to the asset path root),
   * or an empty list if there was any error
   */
  public List<String> getFilesInDirRecursive(String dir, String fileExt, int maxDepth) {
    BiPredicate<Path, BasicFileAttributes> extFilter = (path, attrs) -> FilePaths.getExtension(path).equals(fileExt);
    for (var rootDir : ROOT_DIRS) {
      var dirPath = new File(rootDir + dir).toPath();
      var rootDirPath = new File(rootDir).toPath();
      try (var stream = Files.find(dirPath, maxDepth, extFilter)) {
        return stream
                .map(path -> rootDirPath.relativize(path).toString())
                .collect(Collectors.toList());
      } catch (IOException ignored) {
        // if there is an error, try the next asset root dir
      }
    }

    // no such dir found or error listing files
    return List.of();
  }
}
