package FileIndexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Table {
    private String[] header;
    private String[][] rows;
    private Hashtable<String, ArrayList<Integer>> indices;

    public Table(Hashtable<String, ArrayList<Integer>> indices) {
        this.indices = indices;
        this.rows = new String[0][0];
    }

    public void unload() {
        this.rows = new String[0][0];
    }

    public Hashtable<String, ArrayList<Integer>> getIndexTable() {
        return indices;
    }

    public void loadData(String[][] data) {
        this.header = data[0];
        this.rows = Util.concatenateArrays(this.rows, Arrays.copyOfRange(data, 1, data.length));
    }

    public String[] getHeader() {
        return header;
    }

    public String[][] getRows() {
        return rows;
    }

    public String[][] getByName(String name) {
        ArrayList<Integer> indicesByName = getIndices(name);
        String[][] results = new String[indicesByName.size()][rows[0].length];
        int j = 0;
        for (Integer i : indicesByName) {
            results[j] = rows[i];
            j++;
        }
        return results;
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
