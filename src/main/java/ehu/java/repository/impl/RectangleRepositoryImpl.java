package ehu.java.repository.impl;

import ehu.java.entity.Rectangle;
import ehu.java.repository.*;
import ehu.java.service.RectangleService;

import java.util.*;
import java.util.stream.Collectors;

public class RectangleRepositoryImpl implements Repository<Rectangle> {
    private final Map<Integer, Rectangle> rectangles = new HashMap<>();

    @Override
    public void add(Rectangle rectangle) {
        rectangles.put(rectangle.getId(), rectangle);
    }

    @Override
    public void remove(Rectangle rectangle) {
        rectangles.remove(rectangle.getId());
    }

    @Override
    public List<Rectangle> getAll() {
        return new ArrayList<>(rectangles.values());
    }

    @Override
    public List<Rectangle> query(Specification<Rectangle> specification) {
        return rectangles.values().stream()
                .filter(specification::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rectangle> sort(Comparator<Rectangle> comparator) {
        return rectangles.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
