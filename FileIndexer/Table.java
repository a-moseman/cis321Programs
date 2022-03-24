package FileIndexer;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    private String[] header;
    private String[][] rows;

    public Table(String[][] data) {
        this.header = data[0];
        this.rows = Arrays.copyOfRange(data, 1, data.length);
    }

    public String[] select(String headerName) {
        int index = -1;
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(headerName)) {
                index = i;
                break;
            }
        }
        // if not in header
        if (index == -1) {
            return null;
        }
        String[] result = new String[rows.length];
        for (int i = 0; i < rows.length; i++) {
            result[i] = rows[i][index];
        }
        return result;
    }
}
