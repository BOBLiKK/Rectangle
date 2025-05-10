package ehu.java.repository;

import ehu.java.entity.Rectangle;

import java.util.List;
import java.util.Comparator;

public interface Repository<T> {
    void add(T item);
    void remove(T item);
    List<T> getAll();
    List<T> query(Specification<T> specification);
    List<T> sort(Comparator<T> comparator);
}