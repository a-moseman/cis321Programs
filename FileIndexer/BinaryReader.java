package FileIndexer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;

import FileIndexer.GlyphicalLib.CSV;

public class BinaryReader {
    private static final int ROWS_PER_FILE = 100;

    /**
     * Convert the source file into .dat files.
     */
    public static void initialLoad(String inputPath, String outputPath, String outputFileName, String indexFileName) {
        try {
            initialLoadTable(inputPath, outputPath, outputFileName, indexFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load from compressed binary files to table.
     * 
     * @param dirPath  The directory path.
     * @param fileName The file name.
     * @return Table
     */
    public static String[][] load(String path) {
        try {
            return loadData(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String[][] loadData(String path) throws IOException {
        File file = new File(path);
        String[][] data = new String[0][0];
        return addFileContentsToData(data, readFromCompressedFile(file));
    }

    private static void initialLoadTable(String inputPath, String outputPath, String outputFileName,
            String indexFileName) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(inputPath, "r");
        String line;
        ArrayList<String> lines = new ArrayList<>();
        int lineCount = 0;
        int fileCount = 0;
        String header = null;
        Hashtable<String, ArrayList<Integer>> indices = new Hashtable<>();
        while ((line = randomAccessFile.readLine()) != null) {
            if (header == null) {
                header = line;
            } else {
                lines.add(line);
            }
            String name = pullName(line);
            if (indices.containsKey(name)) {
                indices.get(name).add(lineCount + fileCount * ROWS_PER_FILE);
            } else {
                indices.put(name, new ArrayList<>());
                indices.get(name).add(lineCount + fileCount * ROWS_PER_FILE);
            }
            lineCount++;
            if (lineCount >= ROWS_PER_FILE) {
                lineCount = 0;
                String data = convertToString(lines);
                data = header + '\n' + data;
                BinaryWriter.writeToCompressedFile(outputPath + "//" + outputFileName + "-" + fileCount + ".dat", data);
                lines.clear();
                fileCount++;
            }
        }
        String data = convertToString(lines);
        data = header + '\n' + data;
        BinaryWriter.writeToCompressedFile(outputPath + "//" + outputFileName + "-" + fileCount + ".dat", data);

        randomAccessFile.close();
        BinaryWriter.saveIndexFile(indices, outputPath + "//" + indexFileName);

    }

    private static String pullName(String row) {
        String[] rowFields = Util.split(row, ',', false);
        return rowFields[0];
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

    public static Hashtable<String, ArrayList<Integer>> readTableIndexData(String dirPath, String indexFileName)
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

    private static String convertToString(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
