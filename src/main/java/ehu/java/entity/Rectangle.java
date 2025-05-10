package ehu.java.entity;

import ehu.java.observer.Observable;
import ehu.java.observer.Observer;
import ehu.java.state.RectangleState;
import ehu.java.warehouse.Warehouse;
import java.util.ArrayList;
import java.util.List;

public class Rectangle extends AbstractEntity implements Observable {

    private int id;
    private List<Point> coordinates;
    private RectangleState state;
    private final List<Observer> observers = new ArrayList<>();

    public Rectangle(int id, List<Point> validatedCoordinates) {
        this.id = id;
        this.coordinates = validatedCoordinates;
        addObserver(Warehouse.getInstance());
    }

    public int getId() {
        return id;
    }


    public RectangleState getState() {
        return state;
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }


    public void setState(RectangleState state) {
        this.state = state;
        notifyObservers();
    }

    public void setCoordinates(List<Point> coordinates) {
        this.coordinates = coordinates;
        notifyObservers();
    }

    public void setId(int id) {
        this.id = id;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rectangle ");
        sb.append("id: ").append(id).append(", ");
        sb.append("points: ").append(coordinates);
        sb.append(" state: ").append(state.getDescription()).append(";");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rectangle other = (Rectangle) obj;

        if (id != other.id) return false;
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
        if (coordinates != null) {
            for (Point p : coordinates) {
                result = result * 31 + (p != null ? p.hashCode() : 0);
            }
        }
        return result;
    }
}
