package ru.Bochkarev.Main;

import ru.Bochkarev.TaskOne.Box;

public class Helper {
    // Метод работы с коробкой
    public static void workWithBox(Box<Integer> box) {
        System.out.println("=Действия с коробкой=");
        Integer value = box.get(); // после этого коробка станет пустой
        if (value != null) {
            System.out.println("> Извлечённое значение: " + value);
        } else {
            System.out.println("> Коробка была пустой.");
        }
        System.out.println("> Состояние коробки после извлечения: " + box);
    }

    // Вспомогательный метод: вычисляет максимум в массиве; возвращает null для пустого массива
    public static Integer maxOfArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // Вспомогательный метод: проверяет, есть ли в массиве положительные элементы
    public static boolean hasPositive(int[] arr) {
        if (arr == null) {
            return false;
        }
        for (int v : arr) {
            if (v > 0) {
                return true;
            }
        }
        return false;
    }

}
