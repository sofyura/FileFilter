package com.example.filefilter.exception;

/**
 * Централизованная обработка ошибок.
 * Все методы завершают программу с кодом 1.
 */
public final class ErrorHandler {

    /**
     * Выводит сообщение об ошибке и завершает программу.
     */
    public static void fatal(String message) {
        System.err.println("Ошибка: " + message);
        System.exit(1);
    }

    /**
     * Выводит предупреждение (не фатальная ошибка, программа продолжает работу).
     */
    public static void warn(String message) {
        System.err.println("Предупреждение: " + message);
    }

    // Запрещаем создание экземпляров
    private ErrorHandler() {
        throw new UnsupportedOperationException("Это утилитарный класс");
    }
}