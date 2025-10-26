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
