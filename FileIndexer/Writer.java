package FileIndexer;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Writer {

    public static void save(Table table, String path) {
        try {
            saveTable(table, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveTable(Table table, String path) throws Exception {
        String raw = table.getRaw();
        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(raw);
        bw.close();
    }
}
