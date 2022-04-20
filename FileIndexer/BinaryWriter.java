package FileIndexer;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class BinaryWriter {
    private static final int ROWS_PER_FILE = 100;

    public static void save(Table table, String dirPath, String fileName) {
        try {
            saveTable(table, dirPath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveIndexFile(Table table, String dirPath) throws Exception {
        Hashtable<String, ArrayList<Integer>> data = new Hashtable<>();
        String[][] rows = table.getRows();
        for (int i = 0; i < rows.length; i++) {
            if (data.containsKey(rows[i][0])) {
                data.get(rows[i][0]).add(i);
            } else {
                data.put(rows[i][0], new ArrayList<>());
                data.get(rows[i][0]).add(i);
            }
        }

        StringBuilder output = new StringBuilder();
        for (String key : data.keySet()) {
            output.append('\"').append(key).append('\"')
                    .append(',')
                    .append('\"');

            for (int i = 0; i < data.get(key).size(); i++) {
                output.append(data.get(key).get(i));
                if (i < data.get(key).size() - 1) {
                    output.append(',');
                }
            }
            output.append('\"').append('\n');
        }
        output.deleteCharAt(output.length() - 1);
        writeToCompressedFile(dirPath + "//indexfile.dat", output.toString());
    }

    private static void saveTable(Table table, String dirPath, String fileName) throws Exception {
        saveIndexFile(table, dirPath);
        String headerRaw = Util.rowAsString(table.getHeader());
        String[][] rows = table.getRows();
        // write data files
        int index = 0;
        while (rows.length > 0) {
            String rowsRaw = Util
                    .rowsAsString(
                            Arrays.copyOfRange(rows, 0, Math.min(rows.length - 1, ROWS_PER_FILE - 1)));

            rowsRaw = headerRaw + '\n' + rowsRaw;

            String filePath = dirPath + "//" + fileName + '-' + (index) + ".dat";
            writeToCompressedFile(filePath, rowsRaw);
            if (rows.length < ROWS_PER_FILE) {
                break;
            }
            rows = Arrays.copyOfRange(rows, ROWS_PER_FILE, rows.length - 1);

            index++;
        }
    }

    private static void writeToCompressedFile(String path, String data) throws Exception {
        File file = new File(path);
        file.createNewFile();
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
        byte[] compressedData = CSV.compress(data);
        randomAccessFile.write(compressedData);
        randomAccessFile.close();
    }
}
