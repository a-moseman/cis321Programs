package FileIndexer;

import java.io.RandomAccessFile;

public class BinaryWriter {
    public static void save(Table table, String path) {
        try {
            saveTable(table, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveTable(Table table, String path) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
        String raw = table.getRaw();
        randomAccessFile.writeUTF(raw);
        randomAccessFile.close();
    }
}
