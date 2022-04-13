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
        db.loadBinary(dirPath, fileName);

        Query query = new Query(db.getTable());
        String[] resultSet = query
                .select("name")
                .whereGreaterThan("G")
                .getResultSet();
        for (String field : resultSet) {
            System.out.println(field);
        }
    }
}