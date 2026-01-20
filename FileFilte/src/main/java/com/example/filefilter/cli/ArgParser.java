package com.example.filefilter.cli;

import com.example.filefilter.constants.AppConstants;
import com.example.filefilter.exception.ErrorHandler;

import java.util.ArrayList;
import java.util.List;

public class ArgParser {
    private String outDir = ".";
    private String prefix = "";
    private boolean append = false;
    private boolean shortStats = false;
    private boolean fullStats = false;
    private final List<String> inputFiles = new ArrayList<>();

    public boolean parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-o":
                    if (++i >= args.length) {
                        ErrorHandler.fatal(AppConstants.ERROR_MISSING_OUTPUT_PATH);
                    }
                    outDir = args[i];
                    break;
                case "-p":
                    if (++i >= args.length) {
                        ErrorHandler.fatal(AppConstants.ERROR_MISSING_PREFIX);
                    }
                    prefix = args[i];
                    break;
                case "-a":
                    append = true;
                    break;
                case "-s":
                    shortStats = true;
                    break;
                case "-f":
                    fullStats = true;
                    break;
                default:
                    if (arg.startsWith("-")) {
                        ErrorHandler.fatal(AppConstants.ERROR_UNKNOWN_OPTION + arg);
                    }
                    inputFiles.add(arg);
            }
        }

        if (inputFiles.isEmpty()) {
            ErrorHandler.fatal(AppConstants.ERROR_NO_INPUT_FILES);
        }
        if (!shortStats && !fullStats) {
            ErrorHandler.fatal(AppConstants.ERROR_NO_STATS_MODE);
        }

        return true;
    }

    // Геттеры
    public String getOutDir() { return outDir; }
    public String getPrefix() { return prefix; }
    public boolean isAppend() { return append; }
    public boolean isShortStats() { return shortStats; }
    public boolean isFullStats() { return fullStats; }
    public List<String> getInputFiles() { return inputFiles; }
}