package ru.Bochkarev.Main;

import ru.Bochkarev.TaskOne.Box;
import ru.Bochkarev.TaskOne.BoxFullException;
import ru.Bochkarev.TaskOne.Car;
import ru.Bochkarev.TaskOne.Compare;
import ru.Bochkarev.TaskThree.Accumulator;
import ru.Bochkarev.TaskThree.Collector;
import ru.Bochkarev.TaskTwo.BoxUtils;
import ru.Bochkarev.TaskThree.GenericMethods;


import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.*;

public class Main {

    // Главный метод
    public static void main(String[] args) {
        System.out.println("Выберите задание: ");
        System.out.println("1) Задание 1. №1 ");
        System.out.println("2) Задание 1. №3 ");
        System.out.println("3) Задание 2. №2 ");
        System.out.println("4) Задание 3. №1 ");
        System.out.println("5) Задание 3. №2 ");
        System.out.println("6) Задание 3. №3 ");
        System.out.println("7) Задание 3. №4 ");
        System.out.println("Выберите задание: ");

        Check check = new Check();
        Scanner scan = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        if (scan.hasNextInt()) {
            int choice = scan.nextInt();

            switch (choice) {

                case 1: {
                    System.out.println("Задание 1");

                    // Проверка ввода
                    System.out.println("\nПожалуйста, введите целое число 3:");
                    int userItem = 0;
                    while (true) {
                        System.out.print("Введите число: ");
                        try {
                            userItem = scan.nextInt();
                            if (userItem != 3) {
                                System.out.println("Ошибка: требуется ввести число 3. Попробуйте ещё раз.");
                                continue;
                            }
                            break;
                        } catch (InputMismatchException ime) {
                            System.out.println("Неверный ввод: требуется целое число. Попробуйте ещё раз.");
                            scan.next(); // очистка неверного токена
                        }
                    }

                    // Создаём коробку
                    Box<Integer> insideBox = new Box<>();
                    try {
                        insideBox.put(userItem);
                        System.out.println("Внутри коробки: " + insideBox);
                    } catch (BoxFullException e) {
                        System.out.println("Не удалось положить объект: " + e.getMessage());
                    }

                    // Выполнение действий с коробкой
                    Helper.workWithBox(insideBox);

                    break;
                }
                case 2: {
                    System.out.println("Задание 2");

                    Car a = new Car("Ford", 250);
                    Car b = new Car("Chevrolet", 225);

                    // Сравниваем машину a и b:
                    // Если получили 1 - a > b
                    // Если получили 0 - a = b
                    // Если получили -1 - a < b
                    System.out.println("\n" + a + " сравнить с " + b + " -> " + a.Comparing(b));

                    // Можно создать ru.Bochkarev.TaskOne.Compare для другого типа без изменения интерфейса
                    System.out.println("Сравним длину названия марки машины с числом:");
                    System.out.println("Введите марку машины - ");
                    String carBrand = scanner.nextLine();
                    System.out.println("Введите число - ");
                    int number = check.readInt(scanner);

                    Compare<String> byLetters = (String s) -> Integer.compare(s.length(), number);
                    System.out.println("Результат сравнения: " + byLetters.Comparing(carBrand));

                    break;
                }
                case 3: {
                    System.out.println("Задание 3");

                    List<Box<? extends Number>> boxes = new ArrayList<>();

                    System.out.print("\nВведите количество коробок: ");
                    int quanity = check.readInt(scanner);

                    for (int i = 0; i < quanity; i++) {
                        System.out.print("Введите число для коробки №" + (i + 1) + ": ");
                        while (!scanner.hasNextDouble()) {
                            System.out.print("Ошибка! Введите число: ");
                            scanner.next();
                        }
                        double value = scanner.nextDouble();

                        // создаём коробку с подходящим типом
                        Box<Double> box = new Box<>(value);
                        boxes.add(box);
                    }

                    double max = BoxUtils.findMax(boxes);
                    System.out.println("\nМаксимальное значение среди коробок: " + max);

                    break;
                }
                case 4: {
                    System.out.println("Задание 4");

                    // 1) Длины строк
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
                    List<Integer> lengths = GenericMethods.mapList(strings, s -> (s == null) ? 0 : s.length());
                    System.out.println("\nИсходные строки: " + strings);
                    System.out.println("Длины строк: " + lengths);

                    // 2) Отрицательные числа становятся положительными
                    List<Integer> ints = Arrays.asList(1, -3, 7);
                    List<Integer> absValues = GenericMethods.mapList(ints, n -> (n == null) ? null : Math.abs(n));
                    System.out.println("\nИсходные целые: " + ints);
                    System.out.println("Абсолютные значения: " + absValues);

                    // 3) Максимум каждого массива
                    List<int[]> arrays = new ArrayList<>();
                    arrays.add(new int[] {7, 5, 8});
                    arrays.add(new int[] {-1, -3, -2});
                    arrays.add(new int[] {29});
                    arrays.add(new int[] {});

                    // Используем ссылку на метод maxOfArray
                    List<Integer> maximum = GenericMethods.mapList(arrays, Helper::maxOfArray);
                    System.out.println("\nИсходные массивы: ");
                    for (int[] a : arrays){
                        System.out.println("  " + Arrays.toString(a));
                    }
                    System.out.println("Максимумы массивов: " + maximum);

                    break;

                }
                case 5: {
                    System.out.println("Задание 5");

                    // 1) Удалить строки, имеющие меньше 3 символов
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
                    List<String> filteredStrings = GenericMethods.filterList(strings, s -> s != null && s.length() >= 3);
                    System.out.println("\nИсходные строки: " + strings);
                    System.out.println("После удаления: " + filteredStrings);

                    // 2) Удалить все положительные элементы
                    List<Integer> ints = Arrays.asList(1, -3, 7);
                    List<Integer> nonPositive = GenericMethods.filterList(ints, n -> n != null && n <= 0);
                    System.out.println("\nИсходные числа: " + ints);
                    System.out.println("После удаления: " + nonPositive);

                    // 3) Оставить только те массивы, в которых нет ни одного положительного элемента
                    List<int[]> arrays = new ArrayList<>();
                    arrays.add(new int[] { -33, 3, -5 });
                    arrays.add(new int[] { 2, -3, 0 , 13});
                    arrays.add(new int[] { -61, -7 });

                    // Условие: оставляем массивы, у которых !Helper.hasPositive(arr)
                    List<int[]> noPosArrays = GenericMethods.filterList(arrays, arr -> !Helper.hasPositive(arr));
                    System.out.println("\nИсходные массивы:");
                    for (int[] a : arrays) {
                        System.out.println("  " + Arrays.toString(a));
                    }
                    System.out.println("\nПосле удаления:");
                    for (int[] a : noPosArrays) {
                        System.out.println("  " + Arrays.toString(a));
                    }

                    break;
                }
                case 6: {
                    System.out.println("Задание 6");

                    // 1) Объединение строк
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
                    String combined = GenericMethods.reduce(strings, (a, b) -> a + b, "");
                    System.out.println("\nИсходные строки: " + strings);
                    System.out.println("Объединённая строка: " + combined);

                    // 2) Сумма чисел
                    List<Integer> ints = Arrays.asList(1, -3, 7);
                    Integer sum = GenericMethods.reduce(ints, (a, b) -> a + b, 0);
                    System.out.println("\nИсходные числа: " + ints);
                    System.out.println("Сумма чисел: " + sum);

                    // 3) Подсчёт общего количества элементов
                    List<List<Integer>> arrays = Arrays.asList(
                            Arrays.asList(1, 2, 3),
                            Arrays.asList(4, 5),
                            Arrays.asList(6)
                    );
                    Integer totalCount = GenericMethods.reduce(arrays, (a, b) -> {
                        List<Integer> merged = new ArrayList<>(a);
                        merged.addAll(b);
                        return merged;
                    }, new ArrayList<Integer>()).size();

                    System.out.println("\nИсходные массивы:");
                    for (List<Integer> a : arrays) {
                        System.out.println("  " + a);
                    }
                    System.out.println("Общее количество элементов: " + totalCount);

                    break;
                }
                case 7: {
                    System.out.println("Задание 7");

                    // 1) Разбить список на два подсписка: положительный и отрицательный
                    List<Integer> nums = Arrays.asList(1, -3, 7);

                    Collector<List<List<Integer>>> partFactory = () -> {
                        List<List<Integer>> lists = new ArrayList<>();
                        lists.add(new ArrayList<>()); // положительные
                        lists.add(new ArrayList<>()); // отрицательные
                        return lists;
                    };

                    Accumulator<List<List<Integer>>, Integer> partAdder = (lists, x) -> {
                        if (x == null) return;
                        if (x > 0) lists.get(0).add(x);
                        else if (x < 0) lists.get(1).add(x);
                        else {
                            // здесь мы игнорируем ноль
                        }
                    };

                    List<List<Integer>> partitioned = GenericMethods.collectTo(nums, partFactory, partAdder);
                    System.out.println("\nИсходные числа: " + nums);
                    System.out.println("Положительные: " + partitioned.get(0));
                    System.out.println("Отрицательные: " + partitioned.get(1));

                    // 2) Разбить список на подсписки с одинаковыми длинами
                    List<String> words = Arrays.asList("qwerty", "asdfg", "zx", "qw", "ab", "xyz");

                    Collector<Map<Integer, List<String>>> mapFactory = HashMap::new;
                    Accumulator<Map<Integer, List<String>>, String> mapAdder = (map, s) -> {
                        if (s == null) return;
                        int len = s.length();
                        map.computeIfAbsent(len, k -> new ArrayList<>()).add(s);
                    };

                    Map<Integer, List<String>> groupedByLength = GenericMethods.collectTo(words, mapFactory, mapAdder);
                    System.out.println("\nИсходные строки: " + words);
                    System.out.println("Группировка по длине:");
                    groupedByLength.keySet().stream().sorted()
                            .forEach(k -> System.out.println("  " + k + " -> " + groupedByLength.get(k)));

                    // 3) Вернуть список без дубликатов
                    List<String> wordsWithDup = Arrays.asList("qwerty", "asdfg", "qwerty", "qw");

                    Collector<Set<String>> setFactory = HashSet::new;
                    Accumulator<Set<String>, String> setAdder = (set, s) -> {
                        if (s != null) set.add(s);
                    };

                    Set<String> unique = GenericMethods.collectTo(wordsWithDup, setFactory, setAdder);
                    System.out.println("\nИсходный список: " + wordsWithDup);
                    System.out.println("Без дубликатов: " + unique);

                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}
