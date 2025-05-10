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

    @Test
    void testCalculatePerimeter() {
        Rectangle rectangle = new Rectangle(1, "Test", List.of(
                new Point(0, 0), new Point(0, 4), new Point(3, 4), new Point(3, 0)
        ));
        double perimeter = rectangleService.calculatePerimeter(rectangle);
        Assertions.assertEquals(14.0, perimeter, 0.001, "Perimeter should be 14.0");
    }


    @Test
    void testCalculateArea() {
        Rectangle rectangle = new Rectangle(1, "Test", List.of(
                new Point(0, 0), new Point(0, 4), new Point(3, 4), new Point(3, 0)
        ));
        double area = rectangleService.calculateArea(rectangle);
        Assertions.assertEquals(12.0, area, 0.001, "Area should be 12.0");
    }



    @Test
    void testUpdateRectangleCoordinatesValid() {
        Rectangle rectangle = new Rectangle(1, "Rectangle1", List.of(
                new Point(0, 0), new Point(0, 4), new Point(3, 4), new Point(3, 0)
        ));
        repository.add(rectangle);
        List<Point> newPoints = List.of(
                new Point(1, 1), new Point(1, 5), new Point(4, 5), new Point(4, 1)
        );
        boolean result = rectangleService.updateRectangleCoordinates(1, newPoints, repository);
        Assertions.assertTrue(result, "Update should be successful");
    }

    @Test
    void testUpdateRectangleCoordinatesInvalid() {
        Rectangle rectangle = new Rectangle(1, "Rectangle1", List.of(
                new Point(0, 0), new Point(0, 4), new Point(3, 4), new Point(3, 0)
        ));
        repository.add(rectangle);
        List<Point> invalidPoints = List.of(
                new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)
        );
        boolean result = rectangleService.updateRectangleCoordinates(1, invalidPoints, repository);
        Assertions.assertFalse(result, "Update should fail due to invalid coordinates");
    }
}