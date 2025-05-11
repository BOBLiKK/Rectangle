package service;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.repository.impl.RectangleRepositoryImpl;
import ehu.java.service.RectangleService;
import ehu.java.service.impl.RectangleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class RectangleServiceImplTest {

    private final RectangleService rectangleService = new RectangleServiceImpl();
    private final RectangleRepositoryImpl repository = new RectangleRepositoryImpl();

    private static final Rectangle ORIGINAL_RECTANGLE = new Rectangle(1, List.of(
            new Point(0, 0), new Point(0, 4),
            new Point(3, 4), new Point(3, 0)
    ));

    private static final List<Point> VALID_POINTS = List.of(
            new Point(1, 1), new Point(1, 5),
            new Point(4, 5), new Point(4, 1)
    );

    private static final List<Point> INVALID_POINTS = List.of(
            new Point(0, 0), new Point(1, 2),
            new Point(2, 1), new Point(3, 3)
    );

    private static final List<Point> EQUAL_POINTS = List.of(
            new Point(0, 0), new Point(0, 0),
            new Point(0, 0), new Point(0, 0)
    );

    @Test
    void testCalculatePerimeter() {
        Rectangle rectangle = ORIGINAL_RECTANGLE;
        double perimeter = rectangleService.calculatePerimeter(rectangle);
        Assertions.assertEquals(14.0, perimeter, 0.001, "Perimeter should be 14.0");
    }

    @Test
    void testCalculateArea() {
        Rectangle rectangle = ORIGINAL_RECTANGLE;
        double area = rectangleService.calculateArea(rectangle);
        Assertions.assertEquals(12.0, area, 0.001, "Area should be 12.0");
    }

    @Test
    void testUpdateRectangleCoordinatesValid() {
        repository.add(ORIGINAL_RECTANGLE);
        boolean result = rectangleService.updateRectangleCoordinates(1, VALID_POINTS, repository);
        Assertions.assertEquals(true, result, "Update should be successful");
    }

    @Test
    void testUpdateRectangleCoordinatesInvalid() {
        repository.add(ORIGINAL_RECTANGLE);
        boolean result = rectangleService.updateRectangleCoordinates(1, INVALID_POINTS, repository);
        Assertions.assertEquals(false, result, "Update should fail due to invalid coordinates");
    }

    @Test
    void testUpdateRectangleCoordinatesInvalidWithFourEqualPoints() {
        repository.add(ORIGINAL_RECTANGLE);
        boolean result = rectangleService.updateRectangleCoordinates(1, EQUAL_POINTS, repository);
        Assertions.assertEquals(false, result, "Update should fail due to invalid coordinates");
    }
}
