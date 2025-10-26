package ru.Bochkarev.TaskThree;

@FunctionalInterface
public interface Combiner<T> {
    T combine(T a, T b);
}
