package FileIndexer;

public class Main {
    public static void main(String[] args) {
        String path = "FileIndexer//phone_data_10000.txt";
        Database db = new Database();
        db.load(path);
        db.save("FileIndexer//data.csv");
    }
}