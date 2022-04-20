package FileIndexer;

public class Main {
    public static void main(String[] args) {
        // TODO: check if already intially loaded
        String path = "FileIndexer//10E.csv";
        String dirPath = "FileIndexer//dataFiles";
        String fileName = "data";
        Database db = new Database();
        db.initialBinaryLoad(path);
        db.saveBinary(dirPath, fileName);
        // finish initial set up
        db.loadBinary(dirPath, fileName, "indexfile.dat");
        String query = "name < G && name > D";
        String[][] resultSet = QueryParser.parse(db, query);

        for (String[] row : resultSet) {
            for (String field : row) {
                System.out.print(field + ',');
            }
            System.out.println();
        }
    }
}