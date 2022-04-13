package FileIndexer;

import java.util.ArrayList;

public class Util {
    public static String[] listAsArray(ArrayList<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static String[] split(String string, char delimiter, boolean keepQuotes) {
        ArrayList<String> row = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '"') {
                inQuotes = !inQuotes;
                if (keepQuotes) {
                    field.append('\"');
                }
            } else if (!inQuotes && string.charAt(i) == delimiter) {
                row.add(field.toString());
                field = new StringBuilder();
            } else {
                field.append(string.charAt(i));
            }
        }
        row.add(field.toString()); // Add the last part of the string
        return row.toArray(new String[0]);
    }

    public static String rowAsString(String[] row) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < row.length; i++) {
            output.append(row[i]);
            if (i < row.length - 1) {
                output.append(',');
            }
        }
        return output.toString();
    }

    public static String rowsAsString(String[][] rows) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < rows.length; i++) {
            output.append(rowAsString(rows[i]));
            if (i < rows.length - 1) {
                output.append('\n');
            }
        }
        return output.toString();
    }

    public static String[][] concatenateArrays(String[][] a, String[][] b) {
        String[][] c = new String[a.length + b.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            c[i + a.length] = b[i];
        }
        return c;
    }
}
