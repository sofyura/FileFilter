import java.util.List;

public class StatisticsPrinter {

    public static void printShortStats(List<String> integers, List<String> floats, List<String> strings) {
        System.out.println("=== Краткая статистика ===");
        System.out.println("Целые: " + integers.size());
        System.out.println("Вещественные: " + floats.size());
        System.out.println("Строки: " + strings.size());
    }

    public static void printFullStats(List<String> integers, List<String> floats, List<String> strings) {
        System.out.println("=== Полная статистика ===");
        printIntStats(integers);
        printFloatStats(floats);
        printStringStats(strings);
    }

    private static void printIntStats(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("Целые: нет данных");
            return;
        }
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE, sum = 0;
        for (String s : list) {
            long v = Long.parseLong(s);
            if (v < min) min = v;
            if (v > max) max = v;
            sum += v;
        }
        double avg = (double) sum / list.size();
        System.out.printf("Целые: %d, мин=%d, макс=%d, сумма=%d, среднее=%.4f%n",
                list.size(), min, max, sum, avg);
    }

    private static void printFloatStats(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("Вещественные: нет данных");
            return;
        }
        double min = Double.POSITIVE_INFINITY, max = Double.NEGATIVE_INFINITY, sum = 0;
        for (String s : list) {
            double v = Double.parseDouble(s);
            if (v < min) min = v;
            if (v > max) max = v;
            sum += v;
        }
        double avg = sum / list.size();
        System.out.printf("Вещественные: %d, мин=%.6g, макс=%.6g, сумма=%.6g, среднее=%.6g%n",
                list.size(), min, max, sum, avg);
    }

    private static void printStringStats(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("Строки: нет данных");
            return;
        }
        int minLen = Integer.MAX_VALUE, maxLen = 0;
        for (String s : list) {
            int len = s.length();
            if (len < minLen) minLen = len;
            if (len > maxLen) maxLen = len;
        }
        System.out.printf("Строки: %d, мин. длина=%d, макс. длина=%d%n",
                list.size(), minLen, maxLen);
    }
}