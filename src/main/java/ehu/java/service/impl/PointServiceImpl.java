package ehu.java.service.impl;

import ehu.java.entity.Point;
import ehu.java.service.PointService;
import ehu.java.validator.impl.PointValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PointServiceImpl implements PointService {

    private static final Logger logger = LogManager.getLogger(PointServiceImpl.class);
    private final PointValidatorImpl pointValidator = new PointValidatorImpl();

    @Override
    public double calculateDistance(Point firstPoint, Point secondPoint) {
        double distanceX = secondPoint.getX() - firstPoint.getX();
        double distanceY = secondPoint.getY() - firstPoint.getY();
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    @Override
    public List<Point> orderPoints(List<Point> points) {
        // bottom left point
        Point basePoint = points.stream()
                .min(Comparator.<Point>comparingDouble(Point::getY)
                        .thenComparingDouble(Point::getX))
                .orElseThrow(() -> new IllegalArgumentException("Cannot find base point"));

        return points.stream()
                .sorted((p1, p2) -> {
                    if (p1.equals(basePoint)) return -1;
                    if (p2.equals(basePoint)) return 1;
                    double angle1 = Math.atan2(p1.getY() - basePoint.getY(), p1.getX() - basePoint.getX());
                    double angle2 = Math.atan2(p2.getY() - basePoint.getY(), p2.getX() - basePoint.getX());
                    return Double.compare(angle1, angle2);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Point> createPoints(String input){
        if(pointValidator.isValidCoordinatesLine(input)) {
            List<Point> points = new ArrayList<>();
            String[] parts = input.split(";");
            for (String part : parts) {
                String[] xy = part.split("\\.");
                double x = Double.parseDouble(xy[0]);
                double y = Double.parseDouble(xy[1]);
                points.add(new Point(x, y));
            }
            logger.info("Points created: " + points);
            return orderPoints(points);
        } else{
            logger.error("Incorrect line:" + input);
            return null;
        }
    }

    @Override
    public List<Double> calculateAllDistances(List<Point> points) {
        List<Double> distances = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                distances.add(calculateDistance(points.get(i), points.get(j)));
            }
        }
        return distances;
    }
}
