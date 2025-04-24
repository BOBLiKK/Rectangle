package ehu.java.service;

import ehu.java.entity.Point;

public interface PointService {

    public double calculateDistance(Point firstPoint, Point secondPoint);
    public double calculateAngle(Point firstPoint, Point secondPoint, Point thirdPoint);
    public boolean isRightAngle(Point firstPoint, Point secondPoint, Point thirdPoint);
}
