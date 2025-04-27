package ehu.java.service;

import ehu.java.entity.Rectangle;
import ehu.java.exception.ServiceException;
import java.util.List;

public interface RectangleService {
    double calculatePerimeter(Rectangle rectangle);
    double calculateArea(Rectangle rectangle);
    double defineSmallSide(Rectangle rectangle);
    double defineLargeSide(Rectangle rectangle);
    List<Rectangle> createRectangles() throws ServiceException;
}
