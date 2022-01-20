/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package misc;

import misc.FilePaths;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFilePaths {
  @Test
  void getNameWithoutExt() {
    //@formatter:off
    testGetNameWithoutExt("name.ext.", "name.ext");
    testGetNameWithoutExt("name.ext",  "name");
    testGetNameWithoutExt("name.",     "name");
    testGetNameWithoutExt("name",      "name");
    testGetNameWithoutExt(".ext",      "");
    testGetNameWithoutExt(".",         "");
    testGetNameWithoutExt("",          "");
    //@formatter:on
  }

  private void testGetNameWithoutExt(String path, String name) {
    assertEquals(name, FilePaths.getNameWithoutExt(Path.of(path)));
  }

  @Test
  void getExtension() {
    //@formatter:off
    testGetExtension("name.ext.", "");
    testGetExtension("name.ext",  "ext");
    testGetExtension("name.",     "");
    testGetExtension("name",      "");
    testGetExtension(".ext",      "ext");
    testGetExtension(".",         "");
    testGetExtension("",          "");
    //@formatter:on
  }

  private void testGetExtension(String path, String ext) {
    assertEquals(ext, FilePaths.getExtension(Path.of(path)));
  }

  @Test
  void replaceExtension() {
    //@formatter:off
    testReplaceExtension("name.ext.", "new", "name.ext.new");
    testReplaceExtension("name.ext",  "new", "name.new");
    testReplaceExtension("name.",     "new", "name.new");
    testReplaceExtension("name",      "new", "name.new");
    testReplaceExtension(".ext",      "new", ".new");
    testReplaceExtension(".",         "new", ".new");
    testReplaceExtension("",          "new", ".new");

    testReplaceExtension("name.ext.", "",    "name.ext");
    testReplaceExtension("name.ext",  "",    "name");
    testReplaceExtension("name.",     "",    "name");
    testReplaceExtension("name",      "",    "name");
    testReplaceExtension(".ext",      "",    "");
    testReplaceExtension(".",         "",    "");
    testReplaceExtension("",          "",    "");
    //@formatter:on
  }

  private void testReplaceExtension(String path, String ext, String newPath) {
    assertEquals(newPath, FilePaths.replaceExtension(Path.of(path), ext).toString());
  }

  @Test
  void getFileName() {
    //@formatter:off
    testGetFileName("dir/name.ext/", "name.ext");
    testGetFileName("dir/name.ext.", "name.ext.");
    testGetFileName("dir/name.ext",  "name.ext");
    testGetFileName("name.ext",      "name.ext");
    testGetFileName(".",             ".");
    testGetFileName("",              "");
    testGetFileName("/",             "");
    //@formatter:on
  }

  private void testGetFileName(String path, String name) {
    assertEquals(name, FilePaths.getFileName(Path.of(path)));
  }
}
