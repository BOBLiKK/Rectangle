package ehu.java.warehouse;

import ehu.java.entity.Rectangle;
import ehu.java.service.RectangleService;
import ehu.java.service.impl.RectangleServiceImpl;
import ehu.java.observer.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class Warehouse implements Observer {

    private static final Logger logger = LogManager.getLogger(Warehouse.class);
    private static Warehouse instance;
    private final Map<Integer, RectangleData> dataMap = new HashMap<>();
    private final RectangleService rectangleService = new RectangleServiceImpl();

    private Warehouse() {}

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void addRectangle(Rectangle rectangle) {
        int id = rectangle.getId();
        double perimeter = rectangleService.calculatePerimeter(rectangle);
        double area = rectangleService.calculateArea(rectangle);
        dataMap.put(id, new RectangleData(perimeter, area));
    }

    public int getNumberOfRectangles() {
        return dataMap.size();
    }

    public RectangleData getRectangleData(int rectangleId) {
        return dataMap.get(rectangleId);
    }

    public Map<Integer, RectangleData> getAllRectangleData() {
        return new HashMap<>(dataMap);
    }

    @Override
    public void update(Rectangle rectangle) {
        int id = rectangle.getId();
        double perimeter = rectangleService.calculatePerimeter(rectangle);
        double area = rectangleService.calculateArea(rectangle);
        dataMap.put(id, new RectangleData(perimeter, area));
        logger.info("Updated data in Warehouse for rectangle ID " + id);
        logger.info("  Perimeter: " + perimeter + ", Area: " + area);
    }

    public static class RectangleData {
        private final double perimeter;
        private final double area;

        public RectangleData(double perimeter, double area) {
            this.perimeter = perimeter;
            this.area = area;
        }

        public double getPerimeter() {
            return perimeter;
        }

        public double getArea() {
            return area;
        }
    }
}
