package service;

import ehu.java.entity.Point;
import ehu.java.service.PointService;
import ehu.java.service.impl.PointServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class PointServiceImplTest {

    private final PointService pointService = new PointServiceImpl();

    private static final Point P1 = new Point(0, 0);
    private static final Point P2 = new Point(3, 4);
    private static final String VALID_INPUT = "0.0;1.2;2.4;3.6";
    private static final String INVALID_INPUT = "0.0;1,2;invalid;3.6";

    @Test
    void testCalculateDistance() {
        double distance = pointService.calculateDistance(P1, P2);
        Assertions.assertEquals(5.0, distance, 0.001, "Distance should be 5.0");
    }

    @Test
    void testCreatePointsValidInput() {
        List<Point> points = pointService.createPoints(VALID_INPUT);
        Assertions.assertNotNull(points, "Points should not be null");
        Assertions.assertEquals(4, points.size(), "Should contain 4 points");
        Assertions.assertEquals(new Point(0, 0), points.get(0));
    }

    @Test
    void testCreatePointsInvalidInput() {
        List<Point> points = pointService.createPoints(INVALID_INPUT);
        Assertions.assertNull(points, "Points should be null for invalid input");
    }
}
