import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileWriter {

    public static void writeIfNotEmpty(Path path, List<String> lines, boolean append) {
        if (lines.isEmpty()) return;
        try {
            OpenOption[] options = append ?
                    new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND} :
                    new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING};
            Files.write(path, lines, options);
        } catch (IOException e) {
            System.err.println("Ошибка записи в " + path + ": " + e.getMessage());
        }
    }
}