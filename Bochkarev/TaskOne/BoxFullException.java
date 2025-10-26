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
