package ehu.java.factory;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import java.util.List;

public interface FigureFactory {
    Rectangle createRectangle(String name, List<Point> validatedCoordinates);
    Rectangle createRectangle(int id, String name, List<Point> validatedCoordinates);

}
