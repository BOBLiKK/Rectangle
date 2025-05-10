package ehu.java.util;

public class IDGenerator {
    private static int currentId = 1;
    private static IDGenerator instance;

    private IDGenerator() {
    }

    public static IDGenerator getInstance() {
        if (instance == null) {
            instance = new IDGenerator();
        }
        return instance;
    }

    public  int generateId() {
        return currentId++;
    }
}
