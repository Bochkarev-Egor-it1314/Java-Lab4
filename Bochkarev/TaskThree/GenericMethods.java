package ru.Bochkarev.TaskThree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenericMethods {

    // Задание 3.1
    // Применяет функцию f к каждому элементу source и возвращает список результатов
    public static <T, P> List<P> mapList(List<? extends T> source, Applicator<? super T, ? extends P> f) {
        List<P> result = new ArrayList<>(source.size());
        for (T item : source) {
            P out = f.apply(item);
            result.add(out);
        }
        return result;
    }

    // Задание 3.2
    // Возвращает новый List<T> с элементами, для которых pred.test(...) == true.
    public static <T> List<T> filterList(List<? extends T> source, Tester<? super T> pred) {
        Objects.requireNonNull(source, "source == null");
        Objects.requireNonNull(pred, "pred == null");

        List<T> result = new ArrayList<>(source.size());
        for (T item : source) {
            if (pred.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // Задние 3.3
    // Объединяет два значения из list по правилу combiner, если список пуст - возвращаем identity
    public static <T> T reduce(List<T> list, Combiner<T> combiner, T identity) {
        if (list == null || list.isEmpty()) {
            return identity;
        }

        T result = identity;
        for (T item : list) {
            result = combiner.combine(result, item);
        }
        return result;
    }

    // Задание 3.4
    // Собирает элементы source в коллекцию типа P.
    public static <T, P> P collectTo(List<T> source, Collector<P> factory, Accumulator<P, T> adder) {
        Objects.requireNonNull(factory, "factory == null");
        Objects.requireNonNull(adder, "adder == null");
        P result = factory.createCollection();
        if (source == null) return result;
        for (T item : source) {
            adder.accumulate(result, item);
        }
        return result;
    }
}
