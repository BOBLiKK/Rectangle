package ehu.java.service;

import ehu.java.entity.Point;
import java.util.List;

public interface PointService {

    double calculateDistance(Point firstPoint, Point secondPoint);
    List<Point> orderPoints(List<Point> points);
    List<Point> createPoints(String input);
    List<Double> calculateAllDistances(List<Point> points);
}
