package FileIndexer;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;

import FileIndexer.GlyphicalLib.CSV;

/**
 * Class for writing binary files.
 */
public class BinaryWriter {
    /**
     * Write the index file.
     */
    public static void saveIndexFile(Hashtable<String, ArrayList<Integer>> data, String path) throws Exception {
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
        writeToCompressedFile(path, output.toString());
    }

    /**
     * Write compressed data to a binary file.
     */
    public static void writeToCompressedFile(String path, String data) throws Exception {
        File file = new File(path);
        file.createNewFile();
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
        byte[] compressedData = CSV.compress(data);
        randomAccessFile.write(compressedData);
        randomAccessFile.close();
    }
}
