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
        String[][] resultSet = new String[0][0];
        try {
            resultSet = query
                    .filter("name", '>', "G")
                    .filter("type", '=', "Instant")
                    .filter("cmc", '>', "2")
                    .getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        for (String[] row : resultSet) {
            for (String field : row) {
                System.out.print(field + ',');
            }
            System.out.println();
        }
    }
}