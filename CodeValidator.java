import java.io.FileNotFoundException;

/**
 * Program to validate a .java file.
 * For CIS 321 - Spring 2022 Semester
 * 
 * @author Tye Snyder
 * @author Andrew Moseman
 */
public class CodeValidator {
    public static void main(String[] args) {
        String path = args[0];
        if (path.endsWith(".java")) {
            try {
                String raw = Loader.load(path);
                Validator validator = new Validator();
                int lineNumber = validator.validate(raw);
                if (lineNumber == -1) {
                    System.out.println("File is valid.");
                } else {
                    System.out.println("File fails on line " + lineNumber + ".");
                }
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
                System.out.println("File not Found.");
            }
        } else {
            System.out.println("File must be a .java file.");
        }
    }
}