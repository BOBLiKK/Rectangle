package ehu.java.validator;

import ehu.java.entity.Point;

import java.util.List;

public interface RectangleValidator {
    boolean isValidRectangle(List<Point> points);
    public boolean isRightAngle(Point firstPoint, Point secondPoint, Point thirdPoint);
}
