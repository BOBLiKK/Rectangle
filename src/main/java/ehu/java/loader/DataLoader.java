package ehu.java.loader;

import ehu.java.exception.DaoException;
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

    public List<String> loadDataFromFile() throws DaoException {
        List<String> extractedLines = new ArrayList<>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + FILE_PATH);
            }
            try (Stream<String> lines = new java.io.BufferedReader(
                    new java.io.InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()) {
                lines.forEach(line -> {
                    String cleaned = line.replaceAll("\\s+", "");
                   extractedLines.add(cleaned);
                });
            }
        } catch (IOException e) {
            logger.error("Error reading file: " + e);
            throw new DaoException(e);
        }
        return extractedLines;
    }
}