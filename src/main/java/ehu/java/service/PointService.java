package ehu.java.service;

import ehu.java.entity.Point;

import java.util.List;

public interface PointService {

    public double calculateDistance(Point firstPoint, Point secondPoint);
    public double calculateAngle(Point firstPoint, Point secondPoint, Point thirdPoint);
    public List<Point> orderPoints(List<Point> points);
}
