package FileIndexer;

public class FileIndexer {
    public static void main(String[] args) {
        String path = "cis321Programs//FileIndexer//phone_data_10000.txt";
        Loader loader = new Loader();
        Table table = Loader.load(path);
        try {
            Writer.save(table, "data.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}