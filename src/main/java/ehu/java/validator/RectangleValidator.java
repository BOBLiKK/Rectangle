package ehu.java.validator;

import ehu.java.entity.Point;
import java.util.List;

public interface RectangleValidator {
    boolean isValidRectangle(List<Double> distances ,List<Point> points);
}
