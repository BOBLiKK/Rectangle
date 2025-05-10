package ehu.java.service;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.exception.ServiceException;
import ehu.java.repository.impl.RectangleRepositoryImpl;

import java.util.List;

public interface RectangleService {
    double calculatePerimeter(Rectangle rectangle);
    double calculateArea(Rectangle rectangle);
    double defineSmallSide(Rectangle rectangle);
    double defineLargeSide(Rectangle rectangle);
    boolean updateRectangleCoordinates(int rectangleId, List<Point> newCoordinates, RectangleRepositoryImpl repository);
    List<Rectangle> createRectangles(RectangleRepositoryImpl repository) throws ServiceException;
}
