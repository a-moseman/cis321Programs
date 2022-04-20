package FileIndexer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;

import FileIndexer.GlyphicalLib.CSV;

public class BinaryReader {

    /**
     * Load the source data into a table for processing.
     */
    public static Table initialLoad(String path) {
        try {
            return initialLoadTable(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Load from compressed binary files to table.
     * 
     * @param dirPath  The directory path.
     * @param fileName The file name.
     * @return Table
     */
    public static Table load(String dirPath, String fileName, String indexFileName) {
        try {
            return loadTable(dirPath, fileName, indexFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Table initialLoadTable(String path) throws Exception {
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

    private static byte[] readBytesFromFile(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] buf = new byte[(int) randomAccessFile.length()];
        randomAccessFile.read(buf);
        randomAccessFile.close();
        return buf;
    }

    private static String readFromCompressedFile(File file) throws IOException {
        return CSV.uncompress(readBytesFromFile(file));
    }

    private static String[][] addFileContentsToData(String[][] data, String fileContents) {
        String[] splitRaw = Util.split(fileContents, '\n', false);
        if (data.length == 0) {
            return convertToTable(splitRaw);
        } else {
            return Util.concatenateArrays(data, convertToTable(splitRaw));
        }
    }

    private static String[][] readTableData(String dirPath, String fileName) throws IOException {
        File[] dir = new File(dirPath).listFiles();
        String[][] data = new String[0][0];
        for (int i = 0; i < dir.length; i++) {
            File file = dir[i];
            if (!file.getName().equals("indexfile.dat")) {
                String raw = readFromCompressedFile(file);
                data = addFileContentsToData(data, raw);
            }
        }
        return data;
    }

    private static Hashtable<String, ArrayList<Integer>> readTableIndexData(String dirPath, String indexFileName)
            throws IOException {
        Hashtable<String, ArrayList<Integer>> index = new Hashtable<>();
        File file = new File(dirPath + "//" + indexFileName);
        String raw = readFromCompressedFile(file);
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
        return index;
    }

    private static Table loadTable(String dirPath, String fileName, String indexFileName) throws Exception {
        String[][] data = readTableData(dirPath, fileName);
        Hashtable<String, ArrayList<Integer>> index = readTableIndexData(dirPath, indexFileName);
        return new Table(data, index);
    }

    protected static String[][] convertToTable(String[] array) {
        int rows = array.length;
        int cols = array[0].split(",").length;
        String[][] tableData = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                tableData[r][c] = Util.split(array[r], ',', false)[c];
            }
        }
        return tableData;
    }

    protected static String[][] convertToTable(ArrayList<String> list) {
        int rows = list.size();
        int cols = list.get(0).split(",").length;
        String[][] tableData = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                tableData[r][c] = Util.split(list.get(r), ',', false)[c];
            }
        }
        return tableData;
    }
}
