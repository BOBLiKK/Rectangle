package repository;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.repository.impl.RectangleRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RectangleRepositoryImplTest {

    private final RectangleRepositoryImpl repository = new RectangleRepositoryImpl();

    @Test
    void testAddRectangle() {
        Rectangle rectangle = new Rectangle(1, "Test", List.of(
                new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0)
        ));
        repository.add(rectangle);
        Assertions.assertEquals(1, repository.getAll().size(), "Repository should contain one rectangle");
    }

    @Test
    void testRemoveRectangle() {
        Rectangle rectangle = new Rectangle(1, "Test", List.of(
                new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0)
        ));
        repository.add(rectangle);
        repository.remove(rectangle);
        Assertions.assertTrue(repository.getAll().isEmpty(), "Repository should be empty after removal");
    }
}