import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

public class CodeValidator {
    public static void main(String[] args) {
        boolean isValid = false;
        String path = args[0];
        if (path.endsWith(".java")) {
            try{
                String raw = Loader.load(path);
            Validator validator = new Validator();
            isValid = validator.validate(raw);
            }
            catch (FileNotFoundException exception){
                exception.printStackTrace();
                isValid = false;
            }
        }

        System.out.println(isValid);
    }
}