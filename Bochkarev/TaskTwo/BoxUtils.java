package ru.Bochkarev.TaskTwo;

import ru.Bochkarev.TaskOne.Box;

import java.util.List;

public class BoxUtils {
    // Метод поиска максимума
    public static double findMax(List<Box<? extends Number>> boxes) {
        double max = Double.NEGATIVE_INFINITY; // минимально возможное число

        for (Box<? extends Number> box : boxes) {
            if (!box.isEmpty()) {
                Number value = box.getItem(); // получаем значение, но не вынимаем
                double num = value.doubleValue(); // преобразуем к double
                if (num > max) {
                    max = num;
                }
            }
        }

        return max;
    }
}

