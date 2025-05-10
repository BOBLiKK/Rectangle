package ehu.java.repository.impl;

import ehu.java.entity.Rectangle;
import ehu.java.repository.Specification;

public class RectangleVertexCountSpecificationImpl implements Specification<Rectangle> {
    private final int vertexCount;

    public RectangleVertexCountSpecificationImpl(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    @Override
    public boolean isSatisfiedBy(Rectangle item) {
        return item.getCoordinates().size() == vertexCount;
    }
}
