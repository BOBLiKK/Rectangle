package warehouse;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.warehouse.Warehouse;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Warehouse warehouse;
    private static final Rectangle RECTANGLE = new Rectangle(100, List.of(
            new Point(0, 0),
            new Point(0, 2),
            new Point(3, 2),
            new Point(3, 0)
    ));

    private static final Rectangle UPDATED_RECTANGLE = new Rectangle(100, List.of(
            new Point(0, 0),
            new Point(0, 4),
            new Point(5, 4),
            new Point(5, 0)
    ));

    @BeforeEach
    void setUp() {
        warehouse = Warehouse.getInstance();
        warehouse.addRectangle(RECTANGLE);
    }

    @Test
    void testAddRectangleStoresCorrectData() {
        var data = warehouse.getRectangleData(100);
        assertNotNull(data);
        assertEquals(10.0, data.getPerimeter(), 1e-6);
        assertEquals(6.0, data.getArea(), 1e-6);
    }

    @Test
    void testGetNumberOfRectanglesAfterAdding() {
        int count = warehouse.getNumberOfRectangles();
        assertTrue(count >= 1, "There should be at least one rectangle in the warehouse");
    }

    @Test
    void testGetAllRectangleDataReturnsExpectedEntry() {
        Map<Integer, Warehouse.RectangleData> allData = warehouse.getAllRectangleData();
        assertTrue(allData.containsKey(100));
        assertEquals(6.0, allData.get(100).getArea(), 1e-6);
    }

    @Test
    void testUpdateReplacesData() {
        warehouse.update(UPDATED_RECTANGLE);
        var updatedData = warehouse.getRectangleData(100);
        assertEquals(18.0, updatedData.getPerimeter(), 1e-6);
        assertEquals(20.0, updatedData.getArea(), 1e-6);
    }
}
