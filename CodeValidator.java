public class CodeValidator {
    public static void main(String[] args) {
        boolean isValid = false;
        String path = args[0];
        if (path.endsWith(".java")) {
            String raw = Loader.load(path);
            Validator validator = new Validator();
            isValid = validator.validate(raw);
        }

        System.out.println(isValid);
    }
}