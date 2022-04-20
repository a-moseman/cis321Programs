package FileIndexer;

import java.io.File;

public class Main {
    private static String SOURCE_PATH = "FileIndexer//10E.csv";
    private static String DIR_PATH = "FileIndexer//dataFiles";
    private static String FILE_NAME = "data";
    private static String INDEX_FILE_NAME = "indexfile.dat";

    public static void main(String[] args) {
        Database db = new Database();
        if (!binFilesExist()) {
            db.initialBinaryLoad(SOURCE_PATH);
            db.saveBinary(DIR_PATH, FILE_NAME);
        }
        db.loadBinary(DIR_PATH, FILE_NAME, INDEX_FILE_NAME);
        runDemo(db);
    }

    private static boolean binFilesExist() {
        return (new File(DIR_PATH)).listFiles().length > 0;
    }

    private static void runDemo(Database db) {
        QueryParser qp = new QueryParser(db);
        String query = "name < G && name > D";
        String[][] resultSet = qp.parse(query);
        Util.print2DStringArray(resultSet);
    }
}