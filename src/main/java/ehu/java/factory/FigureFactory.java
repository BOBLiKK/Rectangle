package ehu.java.factory;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import java.util.List;

public interface FigureFactory {
    Rectangle createRectangle(List<Point> validatedCoordinates);
}
