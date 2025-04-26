package ehu.java.service.impl;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.service.PointService;
import ehu.java.service.RectangleService;

import java.util.List;

public class RectangleServiceImpl implements RectangleService {

    private final PointService pointService = new PointServiceImpl();
    private final int NUMBER_OF_VERTEXES = 4;

    @Override
    public double calculatePerimeter(Rectangle rectangle) {
        return defineSmallSide(rectangle)*2 + defineLargeSide(rectangle)*2;
    }

    @Override
    public double calculateArea(Rectangle rectangle) {
        return defineSmallSide(rectangle)*defineLargeSide(rectangle);
    }


    @Override
    public double defineSmallSide(Rectangle rectangle) {
        double smallSide = Double.MAX_VALUE;
        double distanceBetweenTwoNeighbourCoordinates;
        List<Point> rectangleCoordinates = rectangle.getCoordinates();
        for (int i = 0; i < NUMBER_OF_VERTEXES; i++) {
            distanceBetweenTwoNeighbourCoordinates = pointService.calculateDistance(rectangleCoordinates.get(i), rectangleCoordinates.get(i + 1));
            if(distanceBetweenTwoNeighbourCoordinates < smallSide) {
                smallSide = distanceBetweenTwoNeighbourCoordinates;
            }
        }
        return smallSide;
    }

    @Override
    public double defineLargeSide(Rectangle rectangle) {
        double largeSide = 0;
        double distanceBetweenTwoNeighbourCoordinates;
        List<Point> rectangleCoordinates = rectangle.getCoordinates();
        for (int i = 0; i < NUMBER_OF_VERTEXES; i++) {
            distanceBetweenTwoNeighbourCoordinates = pointService.calculateDistance(rectangleCoordinates.get(i), rectangleCoordinates.get(i + 1));
            if(largeSide < distanceBetweenTwoNeighbourCoordinates) {
                largeSide = distanceBetweenTwoNeighbourCoordinates;
            }
        }
        return largeSide;
    }
}
