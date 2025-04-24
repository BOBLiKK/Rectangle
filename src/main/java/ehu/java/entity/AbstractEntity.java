package ehu.java.entity;

public abstract class AbstractEntity {
    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
