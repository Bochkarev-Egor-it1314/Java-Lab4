package ru.Bochkarev.TaskThree;

@FunctionalInterface
public interface Accumulator<P, T> {
    void accumulate(P collection, T value);
}
