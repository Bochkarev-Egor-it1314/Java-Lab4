package ru.Bochkarev.TaskThree;

@FunctionalInterface
public interface Applicator<T, P> {
    P apply(T t);
}
