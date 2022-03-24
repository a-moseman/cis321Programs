package CodeValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for reading the java file text.
 */
public class Loader {
    /**
     * Read the text from a file.
     * 
     * @param path The file path.
     * @return String
     * @throws FileNotFoundException
     */
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