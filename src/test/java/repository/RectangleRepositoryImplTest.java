package repository;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.repository.impl.RectangleRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class RectangleRepositoryImplTest {

    private final RectangleRepositoryImpl repository = new RectangleRepositoryImpl();

    private static final Rectangle TEST_RECTANGLE = new Rectangle(1, List.of(
            new Point(0, 0), new Point(0, 2),
            new Point(2, 2), new Point(2, 0)
    ));

    @Test
    void testAddRectangle() {
        repository.add(TEST_RECTANGLE);
        Assertions.assertEquals(1, repository.getAll().size(), "Repository should contain one rectangle");
    }

    @Test
    void testRemoveRectangle() {
        repository.add(TEST_RECTANGLE);
        repository.remove(TEST_RECTANGLE);
        Assertions.assertEquals(0, repository.getAll().size(), "Repository should be empty after removal");
    }
}
