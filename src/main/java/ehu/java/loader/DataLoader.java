package ehu.java.loader;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.factory.impl.FigureFactoryImpl;
import ehu.java.validator.PointValidator;
import ehu.java.validator.RectangleValidator;
import ehu.java.validator.impl.PointValidatorImpl;
import ehu.java.validator.impl.RectangleValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataLoader {
    private static final Logger logger = LogManager.getLogger(DataLoader.class);
    private static final String FILE_PATH = "rectangles.txt";
    private final PointValidator pointValidator = new PointValidatorImpl();
    //todo fix, initialize PointService here would be mistake
    private final RectangleValidator rectangleValidator = new RectangleValidatorImpl();
    private final FigureFactoryImpl figureFactory = new FigureFactoryImpl();

    public List<Rectangle> loadRectangles() {
        List<Rectangle> rectangles = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH)) {
            if (inputStream == null) {
                //todo maybe throw new Dao
                throw new IOException("Файл не найден: " + FILE_PATH);
            }

            // Читаем строки, преобразуем InputStream в Stream<String>
            try (Stream<String> lines = new java.io.BufferedReader(
                    new java.io.InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()) {

                //todo Часть данныx должна быть некорректной. Если встретилась некорректная строка, приложение должно
                //переходить к следующей строке. Файлы не должны находиться вне каталогов.
                lines.forEach(line -> {
                    String cleaned = line.replaceAll("\\s+", "");
                    if (pointValidator.isValidCoordinatesLine(cleaned)) {
                            String[] parts = cleaned.split(";");
                            List<Point> points = new ArrayList<>();
                            for (String part : parts) {
                                String[] xy = part.split("\\.");
                                double x = Double.parseDouble(xy[0]);
                                double y = Double.parseDouble(xy[1]);
                                points.add(new Point(x, y));
                            }
                            if(rectangleValidator.isValidRectangle(points)) {
                                //todo something with the name, it should either not exist either the names should be different
                                rectangles.add(figureFactory.createRectangle("name_sample", points));

                            }


                    } else {
                        //todo or info but will be with the same colour
                        logger.error("Incorrect line:" + line);
                    }
                });
            }

        } catch (IOException e) {
            //todo throw new Dao exception
            logger.error("Error reading file: " + e);
        }
        return rectangles;
    }
}