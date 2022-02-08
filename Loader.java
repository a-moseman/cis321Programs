import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for reading the java file text.
 */
public class Loader {
    public static String load(String path) {
        StringBuilder text = new StringBuilder();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return text.toString();
    }
}