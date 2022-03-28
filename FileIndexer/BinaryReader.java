package FileIndexer;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BinaryReader extends Reader {
    public static Table load(String path) {
        try {
            return loadTable(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Table loadTable(String path) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
        String line;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = randomAccessFile.readLine()) != null) {
            lines.add(line);
        }
        String[][] data = convertToTable(lines);
        randomAccessFile.close();
        return new Table(data);
    }
}
