package ehu.java.service.impl;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.exception.DaoException;
import ehu.java.exception.ServiceException;
import ehu.java.factory.impl.FigureFactoryImpl;
import ehu.java.loader.DataLoader;
import ehu.java.service.PointService;
import ehu.java.service.RectangleService;
import ehu.java.validator.impl.RectangleValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class RectangleServiceImpl implements RectangleService {
    private static final Logger logger = LogManager.getLogger(RectangleServiceImpl.class);
    private final PointService pointService = new PointServiceImpl();
    private final int NUMBER_OF_VERTEXES = 4;
    private final DataLoader dataLoader = new DataLoader();
    private final RectangleValidatorImpl rectangleValidator = new RectangleValidatorImpl();
    private final FigureFactoryImpl figureFactory = new FigureFactoryImpl();

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
        for (int i = 0; i < NUMBER_OF_VERTEXES-1; i++) {
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
        for (int i = 0; i < NUMBER_OF_VERTEXES-1; i++) {
            distanceBetweenTwoNeighbourCoordinates = pointService.calculateDistance(rectangleCoordinates.get(i), rectangleCoordinates.get(i + 1));
            if(largeSide < distanceBetweenTwoNeighbourCoordinates) {
                largeSide = distanceBetweenTwoNeighbourCoordinates;
            }
        }
        return largeSide;
    }

    @Override
    public List<Rectangle> createRectangles() throws ServiceException {
        List<Rectangle> rectangles = new ArrayList<>();
        try{
            List<String> linesFromFile = dataLoader.loadDataFromFile();
            for(String line : linesFromFile) {
                List<Point> points = pointService.createPoints(line);
                if(points != null){
                    List<Double> distances = pointService.calculateAllDistances(points);
                    if(rectangleValidator.isValidRectangle(distances)){
                        rectangles.add(figureFactory.createRectangle("Rectangle " + rectangles.size(), points));
                    } else{
                        logger.error("Invalid rectangle: " + line);
                    }
                }
            }
        } catch(DaoException e){
            throw new ServiceException("Error loading data from file", e);
        }
        return rectangles;
    }
}
