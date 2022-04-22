package FileIndexer;

import java.io.File;
import java.util.ArrayList;

/**
 * Class representing a database.
 */
public class Database {
    private final String DIR_PATH;
    private final String FILE_NAME;
    private final String INDEX_FILE_NAME;

    private Table table;

    private ArrayList<Integer> loadedFileIndices;

    /**
     * Construct a database.
     * 
     * @param dirPath       The path of the directory to write binary files to.
     * @param fileName      The in-common name of the binary files to read and write
     *                      from and to.
     * @param indexFileName The name of the index file to read and write from and
     *                      to.
     */
    public Database(String dirPath, String fileName, String indexFileName) {
        this.DIR_PATH = dirPath;
        this.FILE_NAME = fileName;
        this.INDEX_FILE_NAME = indexFileName;
        this.loadedFileIndices = new ArrayList<>();
    }

    /**
     * Get the loaded file indices.
     * 
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getLoadedFileIndices() {
        return loadedFileIndices;
    }

    /**
     * Get the table.
     * 
     * @return Table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Load the index file into memory and initialize a new table.
     */
    public void loadIndex() {
        try {
            this.table = new Table(this, BinaryReader.readTableIndexData(DIR_PATH, INDEX_FILE_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a binary file into memory given the index.
     */
    public void load(int index) {
        if (!loadedFileIndices.contains(index)) {
            table.loadData(BinaryReader.load(DIR_PATH + "//" + FILE_NAME + "-" + index + ".dat"));
            loadedFileIndices.add(index);
        }
    }

    /**
     * Load all binary files into memory.
     * This is done whenever a query involves a condition involving a header name
     * other than "name".
     */
    public void loadAll() {
        File[] dir = new File(DIR_PATH).listFiles();
        for (int i = 0; i < dir.length - 1; i++) { // - 1 to ignore indexfile.dat
            load(i);
        }
    }

    /**
     * Unload all rows from memory.
     */
    public void unload() {
        table.unload();
    }

    /**
     * Build the binary files from the source file.
     */
    public void initialBinaryLoad(String sourcePath) {
        BinaryReader.initialLoad(sourcePath, DIR_PATH, FILE_NAME, INDEX_FILE_NAME);
    }
}
