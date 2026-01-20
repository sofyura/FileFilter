package com.example.filefilter.core;

import com.example.filefilter.stats.Statistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public static Statistics process(List<String> inputFiles) {
        List<String> integers = new ArrayList<>();
        List<String> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        for (String fileName : inputFiles) {
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

        return new Statistics(integers, floats, strings);
    }
}