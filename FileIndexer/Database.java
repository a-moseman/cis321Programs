package FileIndexer;

import java.io.File;

public class Database {
    private final String DIR_PATH;
    private final String FILE_NAME;
    private final String INDEX_FILE_NAME;

    private Table table;

    public Database(String dirPath, String fileName, String indexFileName) {
        this.DIR_PATH = dirPath;
        this.FILE_NAME = fileName;
        this.INDEX_FILE_NAME = indexFileName;
    }

    public Table getTable() {
        return table;
    }

    public void loadIndex() {
        try {
            this.table = new Table(BinaryReader.readTableIndexData(DIR_PATH, INDEX_FILE_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(int index) {
        table.loadData(BinaryReader.load(DIR_PATH + "//" + FILE_NAME + "-" + index + ".dat"));
    }

    public void loadAll() {
        File[] dir = new File(DIR_PATH).listFiles();
        for (int i = 0; i < dir.length - 1; i++) { // - 1 to ignore indexfile.dat
            load(i);
        }
    }

    public void initialBinaryLoad(String sourcePath) {
        BinaryReader.initialLoad(sourcePath, DIR_PATH, FILE_NAME, INDEX_FILE_NAME);
    }
}
