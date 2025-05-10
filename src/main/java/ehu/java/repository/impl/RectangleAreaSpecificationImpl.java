package ehu.java.repository.impl;

import ehu.java.entity.Rectangle;
import ehu.java.repository.Specification;
import ehu.java.service.RectangleService;
import ehu.java.service.impl.RectangleServiceImpl;

public class RectangleAreaSpecificationImpl implements Specification<Rectangle> {
    private final double area;
    private final RectangleService rectangleService = new RectangleServiceImpl();

    public RectangleAreaSpecificationImpl(double area) {
        this.area = area;
    }

    @Override
    public boolean isSatisfiedBy(Rectangle item) {
        return rectangleService.calculateArea(item) == area;
    }
}
