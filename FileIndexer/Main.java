package FileIndexer;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static String SOURCE_PATH = "FileIndexer//10E.csv";
    private static String DIR_PATH = "FileIndexer//dataFiles";
    private static String FILE_NAME = "data";
    private static String INDEX_FILE_NAME = "indexfile.dat";

    public static void main(String[] args) {
        Database db = new Database(DIR_PATH, FILE_NAME, INDEX_FILE_NAME);
        if (!binFilesExist()) {
            db.initialBinaryLoad(SOURCE_PATH);
        }
        db.loadIndex();

        runDemo(db);
        // cli(db);
    }

    public static void cli(Database db) {
        Scanner scanner = new Scanner(System.in);
        String input;
        QueryParser qp = new QueryParser(db);
        while (!(input = scanner.nextLine()).equals("exit")) {
            String[][] resultSet = qp.parse(input);
            if (resultSet != null) {
                Util.print2DStringArray(resultSet);
            }
        }
        scanner.close();
    }

    private static boolean binFilesExist() {
        return (new File(DIR_PATH)).listFiles().length > 0;
    }

    private static void runDemo(Database db) {
        QueryParser qp = new QueryParser(db);
        String query = "name < G";
        String[][] resultSet = qp.parse(query);
        if (resultSet != null) {
            Util.print2DStringArray(resultSet);
        }
    }
}