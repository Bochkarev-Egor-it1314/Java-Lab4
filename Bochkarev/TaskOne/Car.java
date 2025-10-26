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
