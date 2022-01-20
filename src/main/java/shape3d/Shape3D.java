/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package shape3d;

import java.util.List;

/**
 * Shared interface for all 3D shapes.
 */
public interface Shape3D {
  List<Shape3D> intersect(Shape3D other);
}
