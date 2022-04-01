package FileIndexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Reader {
    public static Table load(String path) {
        try {
            return loadTable(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Table loadTable(String path) throws Exception {
        String[][] data = convertToTable(readToList(path));
        return new Table(data);

    }

    protected static String[][] convertToTable(ArrayList<String> list) {
        int rows = list.size();
        int cols = list.get(0).split(",").length;
        String[][] tableData = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                tableData[r][c] = Util.split(list.get(r), ',')[c];
            }
        }
        return tableData;
    }

    private static ArrayList<String> readToList(String path) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        return list;
    }
}