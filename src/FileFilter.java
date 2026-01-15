import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileFilter {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Использование: java FileFilter [-s|-f] [-a] [-o путь] [-p префикс] файл1 [файл2 ...]");
            return;
        }

        // 1. Парсинг аргументов
        ArgParser parser = new ArgParser();
        if (!parser.parse(args)) return;

        // 2. Создание выходной директории
        try {
            Files.createDirectories(Paths.get(parser.outDir));
        } catch (IOException e) {
            System.err.println("Не удалось создать директорию вывода: " + e.getMessage());
            return;
        }

        // 3. Сбор данных
        List<String> integers = new ArrayList<>();
        List<String> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        for (String fileName : parser.inputFiles) {
            Path file = Paths.get(fileName);
            if (!Files.exists(file)) {
                System.err.println("Файл не найден (пропущен): " + fileName);
                continue;
            }
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String trimmed = line.trim();
                    if (trimmed.isEmpty()) continue;

                    if (DataClassifier.isInteger(trimmed)) {
                        integers.add(trimmed);
                    } else if (DataClassifier.isFloat(trimmed)) {
                        floats.add(trimmed);
                    } else {
                        strings.add(trimmed);
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка чтения файла " + fileName + ": " + e.getMessage());
            }
        }

        // 4. Запись результатов
        String outDir = parser.outDir;
        String prefix = parser.prefix;
        boolean append = parser.append;

        FileWriter.writeIfNotEmpty(Paths.get(outDir, prefix + "integers.txt"), integers, append);
        FileWriter.writeIfNotEmpty(Paths.get(outDir, prefix + "floats.txt"), floats, append);
        FileWriter.writeIfNotEmpty(Paths.get(outDir, prefix + "strings.txt"), strings, append);

        // 5. Вывод статистики
        if (parser.shortStats) {
            StatisticsPrinter.printShortStats(integers, floats, strings);
        }
        if (parser.fullStats) {
            StatisticsPrinter.printFullStats(integers, floats, strings);
        }
    }
}