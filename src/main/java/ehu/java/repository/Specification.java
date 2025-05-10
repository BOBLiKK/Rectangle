package ehu.java.repository;

public interface Specification<T> {
    boolean isSatisfiedBy(T item);
}