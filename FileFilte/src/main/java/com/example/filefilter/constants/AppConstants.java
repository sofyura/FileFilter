package com.example.filefilter.constants;

/**
 * Глобальные константы приложения.
 */
public final class AppConstants {
    // Имена выходных файлов
    public static final String INTEGERS_FILENAME = "integers.txt";
    public static final String FLOATS_FILENAME = "floats.txt";
    public static final String STRINGS_FILENAME = "strings.txt";

    // Сообщения об ошибках
    public static final String ERROR_MISSING_OUTPUT_PATH = "Ожидался путь после -o";
    public static final String ERROR_MISSING_PREFIX = "Ожидался префикс после -p";
    public static final String ERROR_UNKNOWN_OPTION = "Неизвестная опция: ";
    public static final String ERROR_NO_INPUT_FILES = "Не указаны входные файлы";
    public static final String ERROR_NO_STATS_MODE = "Укажите тип статистики: -s (краткая) или -f (полная)";
    public static final String ERROR_CANNOT_CREATE_DIR = "Не удалось создать директорию вывода: ";

    // Запрещаем создание экземпляров
    private AppConstants() {
        throw new UnsupportedOperationException("Это утилитарный класс");
    }
}
