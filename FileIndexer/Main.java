package FileIndexer;

public class Main {
    public static void main(String[] args) {
        String path = "FileIndexer//10E.csv";
        Database db = new Database();
        db.loadBinary(path);

        Query query = new Query(db.getTable());
        String[] resultSet = query
                .select("name")
                .whereGreaterThan("G")
                .getResultSet();
        for (String field : resultSet) {
            System.out.println(field);
        }

        // db.save("FileIndexer//data.csv");
        db.saveBinary("data"); // TODO: save in chunks as will throw error
    }
}