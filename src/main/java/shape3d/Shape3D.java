package shape3d;

import java.util.List;

public interface Shape3D {
  List<Shape3D> intersect(Shape3D other);
}
