package FileIndexer;

public class FileIndexer {
    public static void main(String[] args) {
        String path = "C://Users//drewm//OneDrive//Desktop//cis321Programs//FileIndexer//phone_data_10000.txt";
        Loader loader = new Loader();
        loader.load(path);
    }
}