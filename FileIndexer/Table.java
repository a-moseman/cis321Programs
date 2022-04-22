package FileIndexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Class representing a table in a database.
 */
public class Table {
    private Database db;
    private String[] header;
    private String[][] rows;
    private Hashtable<String, ArrayList<Integer>> indices;

    /**
     * Construct a table.
     * 
     * @param db      The database.
     * @param indices The index map.
     */
    public Table(Database db, Hashtable<String, ArrayList<Integer>> indices) {
        this.db = db;
        this.indices = indices;
        this.rows = new String[0][0];
    }

    /**
     * Unload all data from memory.
     */
    public void unload() {
        this.rows = new String[0][0];
    }

    /**
     * Get the index map.
     * 
     * @return Hashtable<String, ArrayList<Integer>>
     */
    public Hashtable<String, ArrayList<Integer>> getIndexTable() {
        return indices;
    }

    /**
     * Load data into memory.
     * 
     * @param data The data to load into memory.
     */
    public void loadData(String[][] data) {
        this.header = data[0];
        this.rows = Util.concatenateArrays(this.rows, Arrays.copyOfRange(data, 1, data.length));
    }

    /**
     * Get the header.
     * 
     * @return String[]
     */
    public String[] getHeader() {
        return header;
    }

    /**
     * Get the rows.
     * 
     * @return String[][]
     */
    public String[][] getRows() {
        return rows;
    }

    /**
     * Convert the true index of a row into its relative index in memory.
     * 
     * @param target The true index.
     * @return int
     */
    private int trueIndexToRelative(int target) {
        ArrayList<Integer> loaded = db.getLoadedFileIndices();
        int tFile = (int) (target / 100) - 1; // TODO: remove magic number
        int missing = 0;
        for (int i = 0; i < tFile; i++) {
            if (!loaded.contains(i)) {
                missing++;
            }
        }
        return target - 100 * missing - 1;
    }

    /**
     * Get all rows of the given name.
     * 
     * @param name The name to get by.
     * @return String[][]
     */
    public String[][] getByName(String name) {
        ArrayList<Integer> indicesByName = getIndices(name);
        String[][] results = new String[indicesByName.size()][db.getTable().getRows()[0].length];
        int j = 0;
        for (Integer i : indicesByName) {
            int r = trueIndexToRelative(i);
            results[j] = rows[r];
            j++;
        }
        return results;
    }

    /**
     * Get indices by name.
     * 
     * @param key The name of the row.
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getIndices(String key) {
        return indices.get(key);
    }

    /**
     * Get the table as a string.
     * 
     * @return String
     */
    public String getRaw() {
        String[][] data = new String[rows.length + 1][header.length];
        data[0] = header;
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < header.length; j++) {
                data[i][j] = rows[i - 1][j];
            }
        }
        StringBuilder raw = new StringBuilder();
        for (String[] row : data) {
            for (String field : row) {
                raw.append(field).append(',');
            }
            raw.append('\n');
        }
        return raw.toString();
    }
}
