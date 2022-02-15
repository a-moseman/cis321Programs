import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for reading the java file text.
 */
public class Loader {
    public static String load(String path) throws FileNotFoundException {
        StringBuilder text = new StringBuilder();
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return text.toString();
    }
}