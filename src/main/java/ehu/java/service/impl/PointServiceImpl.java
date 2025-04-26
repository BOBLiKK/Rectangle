package ehu.java.service.impl;

import ehu.java.entity.Point;
import ehu.java.exception.ServiceException;
import ehu.java.service.PointService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PointServiceImpl implements PointService {
    @Override
    public double calculateDistance(Point firstPoint, Point secondPoint) {
        double distanceX = secondPoint.getX() - firstPoint.getX();
        double distanceY = secondPoint.getY() - firstPoint.getY();
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    @Override
    public double calculateAngle(Point firstPoint, Point secondPoint, Point thirdPoint) {

        //todo погрешности

        // Вектор A: secondPoint -> firstPoint
        double ax = firstPoint.getX() - secondPoint.getX();
        double ay = firstPoint.getY() - secondPoint.getY();

        // Вектор B: secondPoint -> thirdPoint
        double bx = thirdPoint.getX() - secondPoint.getX();
        double by = thirdPoint.getY() - secondPoint.getY();

        // Скалярное произведение и длины векторов
        double dotProduct = ax * bx + ay * by;
        double magnitudeA = Math.sqrt(ax * ax + ay * ay);
        double magnitudeB = Math.sqrt(bx * bx + by * by);

        if (magnitudeA == 0 || magnitudeB == 0) {
            return 0; // угол не определён
        }

        double cosTheta = dotProduct / (magnitudeA * magnitudeB);
        // Ограничиваем из-за погрешностей
        cosTheta = Math.max(-1.0, Math.min(1.0, cosTheta));

        return Math.toDegrees(Math.acos(cosTheta));
    }



    @Override
    public List<Point> orderPoints(List<Point> points) {
        // Найти нижнюю левую точку
        Point basePoint = points.stream()
                .min(Comparator.<Point>comparingDouble(Point::getY)
                        .thenComparingDouble(Point::getX))
                .orElseThrow(() -> new IllegalArgumentException("Cannot find base point"));

        // Сортировать остальные по углу относительно basePoint
        return points.stream()
                .sorted((p1, p2) -> {
                    if (p1.equals(basePoint)) return -1;
                    if (p2.equals(basePoint)) return 1;
                    double angle1 = Math.atan2(p1.getY() - basePoint.getY(), p1.getX() - basePoint.getX());
                    double angle2 = Math.atan2(p2.getY() - basePoint.getY(), p2.getX() - basePoint.getX());
                    return Double.compare(angle1, angle2);
                })
                .collect(Collectors.toList());
    }
}
