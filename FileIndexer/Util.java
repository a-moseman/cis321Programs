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

    public static String[] split(String string, char delimiter) {
        ArrayList<String> contents = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '"') {
                inQuotes = !inQuotes;
            } else if (!inQuotes && string.charAt(i) == delimiter) {
                contents.add(content.toString());
                content = new StringBuilder();
            } else {
                content.append(string.charAt(i));
            }
        }
        contents.add(content.toString()); // Add the last part of the string
        return contents.toArray(new String[0]);
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
}
