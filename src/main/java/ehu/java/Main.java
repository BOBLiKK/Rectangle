package ehu.java;

import ehu.java.entity.Rectangle;
import ehu.java.exception.ServiceException;
import ehu.java.repository.impl.RectangleRepositoryImpl;
import ehu.java.service.impl.RectangleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        final Logger logger = LogManager.getLogger(Main.class);
        RectangleServiceImpl rectangleService = new RectangleServiceImpl();
        RectangleRepositoryImpl repository = new RectangleRepositoryImpl();
        try {
            List<Rectangle> rectangles = rectangleService.createRectangles(repository);
            for (Rectangle rectangle : rectangles) {
                logger.info("Rectangle: " + rectangle);
                logger.info("Perimeter: " + rectangleService.calculatePerimeter(rectangle));
                logger.info("Area: " + rectangleService.calculateArea(rectangle));
            }
        } catch (ServiceException e) {
            logger.error("Failed to create rectangles: " + e.getMessage());
        }
    }
}