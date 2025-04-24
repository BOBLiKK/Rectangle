package ehu.java.entity;

import java.util.List;

public class Rectangle extends AbstractEntity {
    private final int id;  // поле идентификатора
    private final String name;  // если выберешь использовать имя, можно оставить id = -1
    private final List<Point> coordinates;

    public Rectangle(int id, String name, List<Point> validatedCoordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = validatedCoordinates;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rectangle ");
        sb.append("id: ").append(id).append(", ");
        sb.append("name: ").append(name).append(", ");
        sb.append("points: ").append(coordinates);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rectangle other = (Rectangle) obj;

        if (id != other.id) return false;
        if (name != null ? !name.equals(other.name) : other.name != null) return false;

        if (coordinates == null && other.coordinates == null) return true;
        if (coordinates == null || other.coordinates == null) return false;
        if (coordinates.size() != other.coordinates.size()) return false;

        for (int i = 0; i < coordinates.size(); i++) {
            if (!coordinates.get(i).equals(other.coordinates.get(i))) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        if (name != null) result = result * 31 + name.hashCode();
        if (coordinates != null) {
            for (Point p : coordinates) {
                result = result * 31 + (p != null ? p.hashCode() : 0);
            }
        }
        return result;
    }
}
