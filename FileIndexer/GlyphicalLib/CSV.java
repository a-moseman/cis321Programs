package FileIndexer.GlyphicalLib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CSV {
    public static byte[] fieldToBytes(String field) {
        return field.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] rowToBytes(String[] row) {
        byte[] bytes = new byte[0];
        for (int i = 0; i < row.length; i++) {
            String field = row[i];
            bytes = ArrayOperations.concatenate(bytes, fieldToBytes(field));
            if (i < row.length - 1) {
                bytes = ArrayOperations.concatenate(bytes, ",".getBytes(StandardCharsets.UTF_8));
            }
        }
        return bytes;
    }

    public static byte[] tableToBytes(String[][] table) {
        byte[] bytes = new byte[0];
        for (int i = 0; i < table.length; i++) {
            String[] row = table[i];
            bytes = ArrayOperations.concatenate(bytes, rowToBytes(row));
            if (i < table.length - 1) {
                bytes = ArrayOperations.concatenate(bytes, "\n".getBytes(StandardCharsets.UTF_8));
            }
        }
        return bytes;
    }

    public static String bytesToTable(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] compress(String table) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(table.getBytes());
            gzip.close();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] compress(String[][] table) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                sb.append(table[i][j]);
                if (j < table[i].length - 1) {
                    sb.append(',');
                }
            }
            if (i < table.length - 1) {
                sb.append('\n');
            }
        }
        return compress(sb.toString());
    }

    public static String uncompress(byte[] bytes) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            GZIPInputStream gzip = new GZIPInputStream(in);
            String string = bytesToTable(gzip.readAllBytes());
            gzip.close();
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}