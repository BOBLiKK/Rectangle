package ehu.java.validator.impl;

import ehu.java.entity.Point;
import ehu.java.service.PointService;
import ehu.java.service.impl.PointServiceImpl;
import ehu.java.validator.RectangleValidator;
import java.util.ArrayList;
import java.util.List;

public class RectangleValidatorImpl implements RectangleValidator {

    //todo not sure whether it is correct
    private final PointService pointService;

    public RectangleValidatorImpl() {
        this.pointService = new PointServiceImpl();
    }


    @Override
    public boolean isValidRectangle(List<Point> points) {
        if (points.size() != 4) {
            return false;
        }

        List<Double> distances = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                distances.add(pointService.calculateDistance(points.get(i), points.get(j)));
            }
        }

        distances.sort(Double::compareTo);

        // Проверка свойств прямоугольника
        double side1 = distances.get(0);
        double side2 = distances.get(1);
        double side3 = distances.get(2);
        double side4 = distances.get(3);
        double diag1 = distances.get(4);
        double diag2 = distances.get(5);

        //todo not sure about the precision

        boolean equalSides = Math.abs(side1 - side2) < 1e-6
                && Math.abs(side3 - side4) < 1e-6;
        boolean equalDiagonals = Math.abs(diag1 - diag2) < 1e-6;

        return equalSides && equalDiagonals;
    }

    @Override
    public boolean isRightAngle(Point firstPoint, Point secondPoint, Point thirdPoint) {
        double angle = pointService.calculateAngle(firstPoint, secondPoint, thirdPoint);
        double degrees = Math.toDegrees(angle);
        return Math.abs(degrees - 90.0) == 0;
    }
}
