import java.util.*;

public class ArgParser {
    public String outDir = ".";
    public String prefix = "";
    public boolean append = false;
    public boolean shortStats = false;
    public boolean fullStats = false;
    public List<String> inputFiles = new ArrayList<>();

    public boolean parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if ("-o".equals(arg)) {
                if (++i >= args.length) return showError("Ожидался путь после -o");
                outDir = args[i];
            } else if ("-p".equals(arg)) {
                if (++i >= args.length) return showError("Ожидался префикс после -p");
                prefix = args[i];
            } else if ("-a".equals(arg)) {
                append = true;
            } else if ("-s".equals(arg)) {
                shortStats = true;
            } else if ("-f".equals(arg)) {
                fullStats = true;
            } else if (arg.startsWith("-")) {
                return showError("Неизвестная опция: " + arg);
            } else {
                inputFiles.add(arg);
            }
        }

        if (inputFiles.isEmpty()) return showError("Не указаны входные файлы");
        if (!shortStats && !fullStats) return showError("Укажите тип статистики: -s (краткая) или -f (полная)");
        return true;
    }

    private boolean showError(String msg) {
        System.err.println("Ошибка: " + msg);
        return false;
    }
}