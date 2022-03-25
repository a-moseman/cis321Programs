package FileIndexer;

public class Main {
    public static void main(String[] args) {
        String path = "FileIndexer//phone_data_10000.txt";
        Database db = new Database();
        db.load(path);

        Query query = new Query(db.getTable());
        String[] resultSet = query
                .select("Name")
                .whereGreaterThan("G")
                .getResultSet();
        for (String field : resultSet) {
            System.out.println(field);
        }

        db.save("FileIndexer//data.csv");
    }
}