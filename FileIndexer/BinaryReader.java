package FileIndexer;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;

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
        File[] dir = new File(dirPath).listFiles();
        String[][] data = new String[0][0];
        for (int i = 0; i < dir.length; i++) {
            File file = dir[i];
            String name = file.getName();
            if (name.equals("indexfile.dat")) {
                continue;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            String raw = randomAccessFile.readUTF();
            String[] splitRaw = Util.split(raw, '\n', false);
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

        Hashtable<String, ArrayList<Integer>> index = new Hashtable<>();
        File file = new File(dirPath + "//indexfile.dat");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        String raw = randomAccessFile.readUTF();
        String[] splitRaw = Util.split(raw, '\n', true);
        for (String line : splitRaw) {
            String[] entry = Util.split(line, ',', true);
            String key = entry[0].substring(1, entry[0].length() - 1);
            String[] indices = entry[1].substring(1, entry[1].length() - 1).split(",");
            ArrayList<Integer> realIndices = new ArrayList<>();
            for (String i : indices) {
                realIndices.add(Integer.parseInt(i));
            }
            index.put(key, realIndices);
        }
        randomAccessFile.close();
        return new Table(data, index);
    }
}
