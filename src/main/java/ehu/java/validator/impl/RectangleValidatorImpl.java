package ehu.java.validator.impl;

import ehu.java.entity.Point;
import ehu.java.validator.RectangleValidator;
import java.util.List;

public class RectangleValidatorImpl implements RectangleValidator {

    private static final double PRECISION = 1e-6;

    @Override
    public boolean isValidRectangle(List<Double> distances, List<Point> points) {
        if (distances.size() != 6 || points.size() != 4) {
            return false;
        }
        boolean allPointsEqual = points.stream().distinct().count() == 1;
        if (allPointsEqual) {
            return false;
        }

        distances.sort(Double::compareTo);
        double side1 = distances.get(0);
        double side2 = distances.get(1);
        double side3 = distances.get(2);
        double side4 = distances.get(3);
        double diag1 = distances.get(4);
        double diag2 = distances.get(5);

        boolean equalSides = Math.abs(side1 - side2) < PRECISION &&
                Math.abs(side3 - side4) < PRECISION;
        boolean equalDiagonals = Math.abs(diag1 - diag2) < PRECISION;

        Point p1 = points.get(0);
        Point p2 = points.get(1);
        Point p3 = points.get(2);
        Point p4 = points.get(3);

        boolean orthogonal = isOrthogonal(p1, p2, p3)
                && isOrthogonal(p2, p3, p4)
                && isOrthogonal(p3, p4, p1)
                && isOrthogonal(p4, p1, p2);

        return equalSides && equalDiagonals && orthogonal;
    }

    private boolean isOrthogonal(Point a, Point b, Point c) {
        double dx1 = b.getX() - a.getX();
        double dy1 = b.getY() - a.getY();
        double dx2 = c.getX() - b.getX();
        double dy2 = c.getY() - b.getY();

        double dot = dx1 * dx2 + dy1 * dy2;
        return Math.abs(dot) < PRECISION;
    }
}
