package service;

import ehu.java.entity.Point;
import ehu.java.service.PointService;
import ehu.java.service.impl.PointServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PointServiceImplTest {

    private final PointService pointService = new PointServiceImpl();

    @Test
    void testCalculateDistance() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        double distance = pointService.calculateDistance(p1, p2);
        Assertions.assertEquals(5.0, distance, 0.001, "Distance should be 5.0");
    }

    @Test
    void testCreatePointsValidInput() {
        String input = "0.0;1.2;2.4;3.6";
        List<Point> points = pointService.createPoints(input);
        Assertions.assertNotNull(points, "Points should not be null");
        Assertions.assertEquals(4, points.size(), "Should contain 4 points");
        Assertions.assertEquals(new Point(0, 0), points.get(0));
    }

    @Test
    void testCreatePointsInvalidInput() {
        String input = "0.0;1,2;invalid;3.6";
        List<Point> points = pointService.createPoints(input);
        Assertions.assertNull(points, "Points should be null for invalid input");
    }
}
