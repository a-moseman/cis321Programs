package FileIndexer;

public class FileIndexer {
    public static void main(String[] args) {
        String path = "FileIndexer//phone_data_10000.txt";
        Loader loader = new Loader();
        Table table = loader.load(path);
        try {
            Writer.save(table, "data.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}