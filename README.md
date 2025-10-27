# Java-Lab4

# Отчет по работе: Реализация задач на Java
Бочкарёв Егор ИТ-13,14

## Общее описание
Данный проект представляет собой консольное приложение на Java, которое реализует 7 различных задач:
1. Задание 1. Обобщенная коробка
2. Задание 2. Сравнимое
3. Задание 3. Поиск максимума
4. Задание 4. Функция
5. Задание 5. Фильтр
6. Задание 6. Сокращение
7. Задание 7. Коллекционирование

Программа предлагает пользователю выбрать задачу через меню, затем выполняет соответствующую функцию.

## Структура проекта
- Класс `Main` с вызовом всех задач и пользовательским интерфейсом
- Класс `Box`, который создает "Коробку", в которой может быть любое число и класс исключения `BoxFullException` для попытки положить объект в уже заполненную коробку
- Класс `Car` и его интерфейс `Compare`, который нужен для сравнения 2 данных
- Класс `BoxUtilits` для поиска максимального элемента из множества коробок
- Класс `GenericMethods`, который содержит
    - Метод `mapList` и его интерфейс `Applicator` для преобразования списков
    - Метод `filterList` и его интерфейс `Tester` для фильтрации списков
    - Метод `reduce` и его интерфейс `Combiner` для сокращения списков
    - Метод `collectTo` с интерфейсами `Collector` и `Accumulator` для коллекционирования
- Класс `Helper`, который содержит
    - Метод `workWithBox` для выполнения действий с коробкой
    - Метод `maxOfArray` для вычисления максимума в массиве
    - Метод `hasPositive` для проверки, есть ли в массиве положительные элементы
- Класс `Check` для проверки входных данных

## Детальный анализ методов

### Задание 1.1 (Обобщенная коробка)

**<ins>Задача:</ins>**

Создайте сущность Коробка, которая обладает следующими характеристиками:
+ Может хранить один произвольный объект в один момент времени.
+ Объект можно получить и разместить на хранение в любой момент времени.
+ Если объект забирают из коробки – ссылку на этот объект необходимо обнулить.
+ Если объект кладут в коробку, но она не пуста – необходимо выкинуть исключение.
+ Имеет метод проверки на заполненность.
+ Методы класса должны работать с тем типом данных, который был указан во время создания объекта.

Создайте Коробку которая может хранить целочисленное значение, разместите туда число 3. Передайте Коробку в какой-либо метод, извлеките значение, и выведите его на экран. 

**<ins>Метод решения:</ins>**

Эта задача решается созданием нового класса `Box`. Внутри класса 1 поле: `item` типа `T`, которое хранит содержимое коробки. Класс имеет 2 конструктора: конструктор по умолчанию, который создает пустую коробку, и конструктор с начальным значением, который сразу размещает объект в коробке.

Все методы класса работают с типом данных, который был указан при создании объекта коробки, благодаря использованию обобщенного типа:

`put(T item)` - размещает объект в коробке. Если коробка уже заполнена, выбрасывает исключение

`get()` - извлекает объект из коробки. После извлечения ссылка на объект обнуляется

`isEmpty()` - проверяет, пуста ли коробка

`toString()` - возвращает строковое представление коробки

`T getItem()` - геттер

Класс исключения `BoxFullException` отвечает за вывод ошибки при попытки положить объект в уже заполненную коробку

В `Main` создается коробка. После в нее кладут число (с клавиатуры), затем достают его и обнуляют ссылку на него и выводится состояние коробки после операции (Пустая или заполненная) благодоря методу `workWithBox`, который отвечает за действия с коробкой. Вывод содержимого коробки идет через метод `toString`.

**<ins>Код реализации:</ins>**
```
package ru.Bochkarev.TaskOne;

public class Box<T> {
    private T item;

    // Конструктор: создаёт пустую коробку
    public Box() {
        this.item = null;
    }

    // Конструктор: создаёт коробку с начальным элементом
    public Box(T initial) {
        this.item = initial;
    }

    // Положить объект в коробку. Если коробка не пуста — бросаем исключение.
    public void put(T newItem) throws BoxFullException {
        if (!isEmpty()) {
            throw new BoxFullException("Коробка уже заполнена объектом => " + item);
        }
        this.item = newItem;
    }

    // Взять объект из коробки
    public T get() {
        T temp = this.item;
        this.item = null; // обнуляем ссылку после извлечения
        return temp;
    }

    // Проверка на заполненность
    public boolean isEmpty() {
        return this.item == null;
    }

    // Геттер
    public T getItem() {
        return item;
    }

    // Сеттер
    public void setItem(T item) {
        this.item = item;
    }

    // Возвращение строки
    @Override
    public String toString() {
        if (isEmpty()) {
            return "~Коробка пуста~";
        } else {
            return "Коробка >>> предмет в ней это " + this.item;
        }
    }
}
```

```
package ru.Bochkarev.TaskOne;

// Класс исключения для попытки положить объект в уже заполненную коробку
public class BoxFullException extends Exception {

    public BoxFullException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ru.Bochkarev.TaskOne.BoxFullException: " + getMessage();
    }
}

```

```
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
```

```
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
```

**<ins>Вывод на экран:</ins>**

Пожалуйста, введите целое число 3:

Введите число: 3

Внутри коробки: Коробка >>> предмет в ней это 3

=Действия с коробкой=

.> Извлечённое значение: 3

.> Состояние коробки после извлечения: Коробка пуста
***

### Задание 1.3 (Сравнимое)

**<ins>Задача:</ins>**

Создайте ссылочный тип Сравнимое, гарантирующий наличие по данной ссылке метода со следующими характеристиками:
+ Называется “сравнить”
+ Принимает объект.
+ Тип принимаемого объекта может быть изменен без изменения самого Сравнимого.
+ Возвращает целое число. 

**<ins>Метод решения:</ins>**

Эта задача решается созданием интерфейса `Compare`. Интерфейс параметризован типом , что позволяет использовать его для сравнения объектов различных типов. Интерфейс содержит один метод: `comparing(T other)` - принимает объект типа `T` и возвращает целое число. Характеристики:

Возвращает отрицательное число, если текущий объект меньше переданного

Возвращает ноль, если объекты равны

Возвращает положительное число, если текущий объект больше переданного

Тип принимаемого объекта может быть изменен без изменения самого интерфейса

Так же для этой задачи созатся вспомогательный класс `Car` для наглядной демонстрации. В `Main` пользователь сначала создаёт двае машины `a` и `b`. Создается объект сравнения с помощью лямбда-выражения. Они сравниваются между собой методом `comparing` и по итогу выводится результат. Затем для демонстрации возможности изменения объекта вместо самого интерфейса пользователя просят ввести любую марку машины и любое число, которые далее будут сравниваться по признаку: больше ли количество букв в марке машины чем введёное число.

**<ins>Код реализации:</ins>**
```
package ru.Bochkarev.TaskOne;

public interface Compare<T> {
    int Comparing(T other);
}
```

```
package ru.Bochkarev.TaskOne;

// Тестовый класс для второй задачи
public class Car implements Compare<Car> {
    private final String brand;
    private final int speed;

    // Конструктор
    public Car(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    // Метод сравнения
    @Override
    public int Comparing(Car other) {
        return Integer.compare(this.speed, other.speed);
    }

    // Возвращение строки
    @Override
    public String toString() {
        return "Марка " + brand + ", скорость " + speed;
    }
}
```

```
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
```

**<ins>Вывод на экран:</ins>**
Марка Ford, скорость 250 сравнить с Марка Chevrolet, скорость 225 -> 1

Сравним длину названия марки машины с числом:

Введите марку машины - 

Ford

Введите число - 

7

Результат сравнения: -1
***

### Задание 2.2 (Поиск максимума)

**<ins>Задача:</ins>**

Создайте метод, принимающий набор Коробок из задачи 3.1.1 и возвращающий максимальное из их значений в формате double. Принимаемые методом Коробки могут быть параметризованы любыми видами чисел.

**<ins>Метод решения:</ins>**

Эта задача решается созданием класса `BoxUtilits`. Класс содержит статический метод `findMax`, который принимает массив коробок с числами. Класс принимает массив типа `Box<? extends Number>` - коробки с любыми числовыми типами. Возвращает максимальное значение в формате `double`.

Реализация:

Инициализация переменной `max` значением `Double.NEGATIVE_INFINITY`

Цикл по всем коробкам с проверкой, что коробка не пустая

Получение числа из коробки методом `getItem()`

Преобразование числа в `double` и сравнение с текущим максимумом

Возврат найденного максимального значения

В `Main` пользователь вводит количество коробок и заполняет их любыми числами. Далее среди их содержимого ищется максимальное значение.

**<ins>Код реализации:</ins>**
```
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
```

```
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
```

**<ins>Вывод на экран:</ins>**
Введите количество коробок: 4

Введите число для коробки №1: 12

Введите число для коробки №2: 3.5

Ошибка! Введите число: 2,7

Введите число для коробки №3: 6

Введите число для коробки №4: 20,1

Максимальное значение среди коробок: 20.1
***

### Задание 3.1 (Функция)

**<ins>Задача:</ins>**

Разработайте такой метод, который будет принимать список значений типа T, и объект имеющий единственный метод apply. Данный метод надо применить к каждому элементу списка, и вернуть новый список значений типа P, при этом типы T и P могут совпадать, а могут не совпадать. Используйте разработанный метод следующим образом:
1. Передайте в метод список со значениями:“qwerty”, “asdfg”, “zx”, а получите список чисел, где каждое число соответствует длине каждой строки.
2. Передайте в метод список со значениями: 1,-3,7, а получите список в котором все отрицательные числа стали положительными, а положительные остались без изменений
3. Передайте в метод список состоящий из массивов целых чисел, а получите список в котором будут только максимальные значения каждого из исходных массивов 

**<ins>Метод решения:</ins>**

Эта задача решается созданием метода `mapList` в классе `GenericMethods` и интерфейса `Applicator`. Интерфейс `Applicator` параметризован двумя типами `<T, P>` и содержит один метод `apply`, который принимает значение типа `T` и возвращает значение типа `P`. Класс `GenericMethods` содержит статический обобщенный метод `mapList` - принимает список элементов типа T и функцию преобразования, возвращает новый список типа P.

Реализация:

Создает новый `ArrayList` для результатов

Для каждого элемента исходного списка применяет функцию `f.apply(item)`

Добавляет результат преобразования в результирующий список

Возвращает новый список с преобразованными значениями

Работает это со строками, числами и массивами. Как и сказано в задании.

В `Main` демонстрируется работа указанных заданий. Данные преобразуются и выводятся отдельным уже преобразованным списком.

**<ins>Код реализации:</ins>**
```
package ru.Bochkarev.TaskThree;

@FunctionalInterface
public interface Applicator<T, P> {
    P apply(T t);
}
```

```
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
```

```
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
```

```
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
```

**<ins>Вывод на экран:</ins>**
Исходные строки: [qwerty, asdfg, zx]

Длины строк: [6, 5, 2]

Исходные целые: [1, -3, 7]

Абсолютные значения: [1, 3, 7]

Исходные массивы: 

  [7, 5, 8]
  
  [-1, -3, -2]
  
  [29]
  
  []
  
Максимумы массивов: [8, -1, 29, null]
***

### Задание 3.2 (Фильтр)

**<ins>Задача:</ins>**

Разработайте такой метод, который будет принимать список значений типа T и объект имеющий единственный метод test (принимает T и возвращает boolean). Верните новый список типа T, из которого удалены все значения не прошедшие проверку условием. Используйте разработанный метод следующим образом:
1. Передайте в метод список со значениями: “qwerty”, “asdfg”, “zx”, и отфильтруйте все строки имеющие менее трех символов
2. Передайте в метод список со значениями: 1,-3,7, и отфильтруйте все положительные элементы
3. Передайте в метод список состоящий из массивов целых чисел, а получите список в котором будут только те массивы, в которых нет ни одного положительного элемента 

**<ins>Метод решения:</ins>**

Эта задача решается созданием метода `filterList` в классе `GenericMethods` и интерфейса "Tester". Интерфейс `Tester` параметризован типом и содержит один метод `test`, который принимает значение типа T и возвращает boolean. Класс `GenericMethods` содержит статический обобщенный метод `filterList` - принимает список элементов типа `T` и предикат для фильтрации, возвращает новый список типа `T`

Реализация:

Проверяет элементы `source` и `pred` на `null`

Создает новый `ArrayList` для результатов

Для каждого элемента исходного списка проверяет условие `pred.test(item)`

Добавляет элемент в результат только если условие истинно

Возвращает новый список с отфильтрованными значениями

Работает это с числами, строками и массивами.

В `Main` демонстрируется работа указанных заданий. Данные преобразуются и выводятся отдельным уже преобразованным списком.

**<ins>Код реализации:</ins>**
```
package ru.Bochkarev.TaskThree;

@FunctionalInterface
public interface Tester<T> {
    boolean test(T t);
}
```

```
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
```

```
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
```

```
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
```

**<ins>Вывод на экран:</ins>**
Исходные строки: [qwerty, asdfg, zx]

После удаления: [qwerty, asdfg]

Исходные числа: [1, -3, 7]

После удаления: [-3]

Исходные массивы:

  [-33, 3, -5]
  
  [2, -3, 0, 13]
  
  [-61, -7]

После удаления:

  [-61, -7]
***

### Задание 3.3 (Сокращение)

**<ins>Задача:</ins>**

Разработайте такой метод, который будет принимать список значений типа T и способ с помощью которого список значений можно свести к одному значению типа T, которое и возвращается из метода.  Используйте разработанный метод следующим образом:
1. Передайте в метод список со значениями:  “qwerty”, “asdfg”, “zx”, и сформируйте одну большую строку, которая состоит из всех строк исходного списка.
2. Передайте в метод список со значениями: 1,-3,7, и верните сумму всех значений исходного списка.
3. Имеется список, состоящий из списков целых чисел, получите общеe количество элементов во всех списках. Подсказка: решить задачу можно в одно действие или последовательно использовать методы из 3.3.1 и 3.3.3.

Далее необходимо изменить разработанный метод таким образом, чтобы данный метод гарантированно не возвращал null и не выбрасывал ошибок в том случае, если исходный список пуст. 

**<ins>Метод решения:</ins>**

Эта задача решается созданием метода `reduce` в классе `GenericMethods` и интерфейса `Combiner`. Интерфейс `Combiner` параметризован типом и содержит один метод `combine`, который принимает два значения типа `T` (аккумулятор и текущий элемент) и возвращает новый аккумулятор типа `T`. Класс `GenericMethods` содержит статический обобщенный метод `reduce` - принимает список элементов типа `T`, операцию сокращения и начальное значение

Реализация:

Проверяет список `list` на `null`

Инициализирует результат начальным значением

Последовательно применяет операцию combiner.combine(result, item) к каждому элементу

Возвращает финальное значение аккумулятора

Работает с числами, строками и массивами.

В `Main` демонстрируется работа указанных заданий. Алгоритм начинает работать и на вывод подается результирующий массив.

**<ins>Код реализации:</ins>**
```
package ru.Bochkarev.TaskThree;

public interface Collector<P> {
    P createCollection();
}
```

```
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
```

```
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
```

**<ins>Вывод на экран:</ins>**
Исходные строки: [qwerty, asdfg, zx]

Объединённая строка: qwertyasdfgzx

Исходные числа: [1, -3, 7]

Сумма чисел: 5

Исходные массивы:

  [1, 2, 3]
  
  [4, 5]
  
  [6]
  
Общее количество элементов: 6
***

### Задание 3.4 (Сокращение)

**<ins>Задача:</ins>**

Разработайте такой метод, который будет возвращать коллекцию типа P со значениями типа T. Данный метод будет принимать:
1. Список исходных значений
2. Способ создания результирующей коллекции
3. Способ передачи значений исходного списка в результирующую коллекцию.

Используйте разработанный метод следующим образом:
1. Передайте в метод список со значениями: 1,-3,7, и верните их разбитыми на два подсписка, в одном из которых будут только положительные числа, а в другом только отрицательные.
2. Передайте в метод список со значениями:  “qwerty”, “asdfg”, “zx”, “qw” и верните их разбитыми на подсписки таким образом, чтобы в любом подсписке были строки только одинаковой длины
3. Передайте в метод список со значениями:  “qwerty”, “asdfg”, “qwerty”, “qw” и верните набор такого вида, который не может содержать одинаковые объекты. 

**<ins>Метод решения:</ins>**

Эта задача решается созданием класса `collectTo` в классе `GenericMethods` и интерфейсов `Collector` и `Accumulator`. Интерфейс `Collector` параметризован типом <P> и содержит метод `createCollection`, который создает новую коллекцию типа `P`. Интерфейс `Accumulator` параметризован типами `<P, T>` и содержит метод `accumulate`, который добавляет элемент типа `T` в коллекцию типа `P`. Класс `GenericMethods` содержит статический обобщенный метод `collectTo` - принимает список элементов типа `T`, стратегию создания коллекции и стратегию наполнения.

Реализация:

Проверяет `factory` и `adder` на `null`

Создает коллекцию с помощью factory.createCollection()

Для каждого элемента применяет adder.accumulate(result, item)

Возвращает наполненную коллекцию типа `P`

Работает с числами, строками и массивами.

В `Main` демонстрируется работа указанных заданий. Данные преобразуются и выводятся отдельным уже итоговым списком.

**<ins>Код реализации:</ins>**
```
package ru.Bochkarev.TaskThree;

@FunctionalInterface
public interface Accumulator<P, T> {
    void accumulate(P collection, T value);
}
```

```
package ru.Bochkarev.TaskThree;

public interface Collector<P> {
    P createCollection();
}
```

```
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
```

```
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
```

**<ins>Вывод на экран:</ins>**
Исходные числа: [1, -3, 7]

Положительные: [1, 7]

Отрицательные: [-3]

Исходные строки: [qwerty, asdfg, zx, qw, ab, xyz]

Группировка по длине:

  2 -> [zx, qw, ab]
  
  3 -> [xyz]
  
  5 -> [asdfg]
  
  6 -> [qwerty]

Исходный список: [qwerty, asdfg, qwerty, qw]

Без дубликатов: [qw, qwerty, asdfg]
***

## Вспомогательные методы

+ `public int readInt(Scanner scanner)`

Что делает:

Читает из Scanner целое число, пока пользователь не введёт корректное значение.

Как работает (пошагово):
- Заходит в бесконечный цикл while (true).
- Печатает приглашение "Введите целое число: ".
- Проверяет scanner.hasNextInt() — есть ли следующий токен, который можно распарсить как int.
- Если true: читает int num = scanner.nextInt(); и возвращает num.
- Иначе: печатает сообщение об ошибке и вызывает scanner.next() — чтобы "съесть" неверный токен (иначе hasNextInt() будет снова false и цикл застрянет).

Примеры:

При вводе 42 вернёт 42; при вводе abc — попросит ввести ещё раз.

Код:
```
public int readInt(Scanner scanner) {
        while (true) {
            System.out.print("Введите целое число: ");
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                return num;
            } else {
                System.out.println("Ошибка: введите целое число!");
                scanner.next(); // очищаем неверный ввод
            }
        }
    }
```

+ 
