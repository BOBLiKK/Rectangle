package ehu.java.repository.impl;

import ehu.java.entity.Rectangle;
import ehu.java.repository.Specification;

public class RectangleIdSpecificationImpl implements Specification<Rectangle> {
    private final int id;

    public RectangleIdSpecificationImpl(int id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(Rectangle item) {
        return item.getId() == id;
    }
}
