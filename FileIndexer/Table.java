package FileIndexer;

import java.util.Arrays;

public class Table {
    private String[] header;
    private String[][] rows;

    public Table(String[][] data) {
        this.header = data[0];
        this.rows = Arrays.copyOfRange(data, 1, data.length);
    }
}
