import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    private static int c;
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("text"))) {
            reader.lines()
                    .map(Main::extractCoordinates)
                    .mapToLong(Main::calculateSegmentLength)
                    .max()
                    .ifPresent(maxLength -> System.out.println("Max length: " + maxLength));
        } catch (IOException e) {
            System.out.println("File reading error" + e.getMessage() );
        }
    }

    // Метод для извлечения координат из строки
    private static String[] extractCoordinates(String line) {
        Pattern pattern = Pattern.compile("\\((x:(\\d+)),(y:(\\d+))\\)-\\((x:(\\d+)),(y:(\\d+))\\)");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            return new String[]{matcher.group(2), matcher.group(4), matcher.group(6), matcher.group(8)};
        } else {
            throw new IllegalArgumentException("Invalid input format: " + line);
        }
    }

    // Метод для вычисления длины отрезка
    private static long calculateSegmentLength(String[] coordinates) {
        c++;
        int x1 = Integer.parseInt(coordinates[0]);
        int y1 = Integer.parseInt(coordinates[1]);
        int x2 = Integer.parseInt(coordinates[2]);
        int y2 = Integer.parseInt(coordinates[3]);
        double length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.println("Segment " + c + " length is : " + Math.round(length));
        return Math.round(length);
    }
}
