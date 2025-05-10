package ehu.java.repository.impl;

import ehu.java.entity.Rectangle;
import ehu.java.repository.Specification;
import ehu.java.service.RectangleService;
import ehu.java.service.impl.RectangleServiceImpl;

public class RectanglePerimeterSpecificationImpl implements Specification<Rectangle> {
    private final double perimeter;
    private final RectangleService rectangleService = new RectangleServiceImpl();

    public RectanglePerimeterSpecificationImpl(double perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public boolean isSatisfiedBy(Rectangle item) {
        return rectangleService.calculatePerimeter(item) == perimeter;
    }
}
