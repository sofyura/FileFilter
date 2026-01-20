package com.example.filefilter;

import com.example.filefilter.cli.ArgParser;
import com.example.filefilter.constants.AppConstants;
import com.example.filefilter.core.FileProcessor;
import com.example.filefilter.exception.ErrorHandler;
import com.example.filefilter.io.ResultWriter;
import com.example.filefilter.stats.StatisticsPrinter;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        ArgParser parser = new ArgParser();
        parser.parse(args); // fatal error если аргументы некорректны

        try {
            var outputDir = Paths.get(parser.getOutDir());
            java.nio.file.Files.createDirectories(outputDir);
        } catch (Exception e) {
            ErrorHandler.fatal(AppConstants.ERROR_CANNOT_CREATE_DIR + e.getMessage());
        }

        try {
            var result = FileProcessor.process(parser.getInputFiles());
            ResultWriter.write(
                    Paths.get(parser.getOutDir()),
                    parser.getPrefix(),
                    parser.isAppend(),
                    result.integers(),
                    result.floats(),
                    result.strings()
            );

            if (parser.isShortStats()) {
                StatisticsPrinter.printShort(result);
            }
            if (parser.isFullStats()) {
                StatisticsPrinter.printFull(result);
            }

        } catch (Exception e) {
            ErrorHandler.fatal("Неожиданная ошибка: " + e.getMessage());
        }
    }
}