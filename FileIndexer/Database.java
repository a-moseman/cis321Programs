package FileIndexer;

import java.io.File;
import java.util.ArrayList;

public class Database {
    private final String DIR_PATH;
    private final String FILE_NAME;
    private final String INDEX_FILE_NAME;

    private Table table;

    private ArrayList<Integer> loadedFileIndices;

    public Database(String dirPath, String fileName, String indexFileName) {
        this.DIR_PATH = dirPath;
        this.FILE_NAME = fileName;
        this.INDEX_FILE_NAME = indexFileName;
        this.loadedFileIndices = new ArrayList<>();
    }

    public ArrayList<Integer> getLoadedFileIndices() {
        return loadedFileIndices;
    }

    public Table getTable() {
        return table;
    }

    public void loadIndex() {
        try {
            this.table = new Table(this, BinaryReader.readTableIndexData(DIR_PATH, INDEX_FILE_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(int index) {
        if (!loadedFileIndices.contains(index)) {
            table.loadData(BinaryReader.load(DIR_PATH + "//" + FILE_NAME + "-" + index + ".dat"));
            loadedFileIndices.add(index);
        }
    }

    public void loadAll() {
        File[] dir = new File(DIR_PATH).listFiles();
        for (int i = 0; i < dir.length - 1; i++) { // - 1 to ignore indexfile.dat
            load(i);
        }
    }

    public void unload() {
        table.unload();
    }

    public void initialBinaryLoad(String sourcePath) {
        BinaryReader.initialLoad(sourcePath, DIR_PATH, FILE_NAME, INDEX_FILE_NAME);
    }
}
