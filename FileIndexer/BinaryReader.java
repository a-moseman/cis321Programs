package FileIndexer;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BinaryReader extends Reader {
    public static Table initialLoad(String path) {
        try {
            return initialLoadTable(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Table load(String dirPath, String fileName) {
        try {
            return loadTable(dirPath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Table initialLoadTable(String path) throws Exception {
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

    public static Table loadTable(String dirPath, String fileName) throws Exception {
        // TODO: read from each file previously saved
        File[] dir = new File(dirPath).listFiles();
        String[][] data = new String[0][0];
        for (int i = 0; i < dir.length; i++) {
            File file = dir[i];
            String name = file.getName();
            if (name.equals("indexfile.dat")) {
                continue;
            }
            // String shouldBeInt = name.replace(fileName + '-', "").replace(".dat", "");
            // System.out.println(shouldBeInt); // DEBUG
            // int index = Integer.parseInt(shouldBeInt);
            // build data
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            String raw = randomAccessFile.readUTF();
            String[] splitRaw = Util.split(raw, '\n');
            ArrayList<String> lines = new ArrayList<>();
            for (String line : splitRaw) {
                lines.add(line);
            }
            if (data.length == 0) {
                data = convertToTable(lines);
            } else {
                data = Util.concatenateArrays(data, convertToTable(lines));
            }
            randomAccessFile.close();
        }
        return new Table(data);
    }
}
