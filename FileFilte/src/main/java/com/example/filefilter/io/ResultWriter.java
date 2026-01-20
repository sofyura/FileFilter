package com.example.filefilter.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ResultWriter {

    public static void write(
            Path outDir,
            String prefix,
            boolean append,
            List<String> integers,
            List<String> floats,
            List<String> strings
    ) throws IOException {
        writeIfNotEmpty(outDir.resolve(prefix + "integers.txt"), integers, append);
        writeIfNotEmpty(outDir.resolve(prefix + "floats.txt"), floats, append);
        writeIfNotEmpty(outDir.resolve(prefix + "strings.txt"), strings, append);
    }

    private static void writeIfNotEmpty(Path path, List<String> lines, boolean append) throws IOException {
        if (lines.isEmpty()) return;
        OpenOption[] options = append ?
                new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND} :
                new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING};
        Files.write(path, lines, options);
    }
}