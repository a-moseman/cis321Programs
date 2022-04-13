package FileIndexer;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class BinaryWriter {
    private static final int ROWS_PER_FILE = 100;

    public static void save(Table table, String path) {
        try {
            saveTable(table, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveIndexFile(Table table) throws Exception {
        // TODO: test
        Hashtable<String, ArrayList<Integer>> data = new Hashtable<>();
        String[][] rows = table.getRows();
        for (int i = 0; i < rows.length; i++) {
            if (data.containsKey(rows[i][0])) {
                data.get(rows[i][0]).add(i);
            }
            else {
                data.put(rows[i]][0], new ArrayList<>());
                data.get(rows[i][0]).add(i);
            }
        }

        StringBuilder output = new StringBuilder();
        for (String key : data.keySet()) {
            output.append(key)
                .append(',')
                .append('\"');

            for (Integer i : data.get(key)) {
                output.append(i).append(',');
            }
            output.append('\"').append('\n');
        }

        File file = new File("indexfile.dat");
        file.createNewFile();
        RandomAccessFile randomAccessFile = new RandomAccessFile("indexfile.dat", "rw");
        randomAccessFile.writeUTF(output.toString());
        randomAccessFile.close();
    }

    private static void saveTable(Table table, String path) throws Exception {
        // TODO: test
        saveIndexFile(table);
        String headerRaw = Util.rowAsString(table.getHeader());
        String[][] rows = table.getRows();
        // write data files
        int rowsSaved = 0;
        while (rowsSaved < rows.length) {
            File file = new File(path);
            file.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(path + '-' + (rowsSaved / ROWS_PER_FILE), "rw");
            // convert data
            String rowsRaw = Util
                    .rowsAsString(
                            Arrays.copyOfRange(rows, rowsSaved, Math.min(rowsSaved + ROWS_PER_FILE, rows.length)));

            rowsRaw = headerRaw + '\n' + rowsRaw;
            // write data
            randomAccessFile.writeUTF(rowsRaw);
            randomAccessFile.close();
            rowsSaved += ROWS_PER_FILE;
            rows = Arrays.copyOfRange(rows, rowsSaved, Math.min(rowsSaved + ROWS_PER_FILE, rows.length));
        }
    }
}
