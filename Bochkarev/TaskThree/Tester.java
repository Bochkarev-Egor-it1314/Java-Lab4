package ru.Bochkarev.TaskThree;

@FunctionalInterface
public interface Tester<T> {
    boolean test(T t);
}

