package FileIndexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Table {
    private String[] header;
    private String[][] rows;
    private Hashtable<String, ArrayList<Integer>> indices; // TODO: try tree hash?

    public Table(String[][] data) {
        this.header = data[0];
        this.rows = Arrays.copyOfRange(data, 1, data.length);
        this.indices = null;
    }

    public Table(String[][] data, Hashtable<String, ArrayList<Integer>> indices) {
        this.header = data[0];
        this.rows = Arrays.copyOfRange(data, 1, data.length);
        this.indices = indices;
    }

    public String[] getHeader() {
        return header;
    }

    public String[][] getRows() {
        return rows;
    }

    public ArrayList<Integer> getIndices(String key) {
        return indices.get(key);
    }

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
