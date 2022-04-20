package FileIndexer;

import java.util.ArrayList;

public class Query {
    private static final int ROWS_PER_FILE = 100;

    public static String[][] query(Database db, String headerName, char operator, String toCompareTo) {
        db.loadAll();
        // perform the query
        if (headerName.equals("name")) {
            return query(db, operator, toCompareTo);
        }
        try {
            return filter(db.getTable(), headerName, operator, toCompareTo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String[][] query(Database db, char operator, String toCompareTo) {
        // determine which files to load
        ArrayList<String> names = new ArrayList<>(db.getTable().getIndexTable().keySet());
        ArrayList<Integer> indices = new ArrayList<>();
        for (String name : names) {
            if (compare(name, operator, toCompareTo)) {
                for (Integer i : db.getTable().getIndices(name)) {
                    indices.add(i);
                }
            }
        }
        ArrayList<Integer> fileIndices = new ArrayList<>();
        for (Integer index : indices) {
            int i = index / ROWS_PER_FILE;
            if (!fileIndices.contains(i)) {
                fileIndices.add(i);
            }
        }
        // load the needed files
        for (Integer index : fileIndices) {
            db.load(index);
        }
        // perform the query
        try {
            return filter(db.getTable(), "name", operator, toCompareTo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Search the header for a matching name and return the index.
     * Returns -1 if the name is missing.
     * 
     * @param headerName
     * @return int
     */
    private static int searchHeaderIndexByName(Table table, String headerName) {
        for (int i = 0; i < table.getHeader().length; i++) {
            if (table.getHeader()[i].equals(headerName)) {
                return i;
            }
        }
        return -1;
    }

    private static ArrayList<String[]> filterDataByCondition(String[][] data, int headerIndex, char operator,
            String toCompareTo) {
        ArrayList<String[]> resultList = new ArrayList<>();
        for (String[] row : data) {
            if (compare(row[headerIndex], operator, toCompareTo)) {
                resultList.add(row);
            }
        }
        return resultList;
    }

    private static String[][] filter(Table table, String headerName, char operator, String toCompareTo)
            throws Exception {
        int headerIndex = searchHeaderIndexByName(table, headerName);
        if (headerIndex < 0) {
            throw new Exception("Header name not found.");
        }
        ArrayList<String[]> filteredData = filterDataByCondition(table.getRows(), headerIndex, operator, toCompareTo);
        String[][] newResultSet = new String[filteredData.size()][table.getRows()[0].length];
        for (int i = 0; i < newResultSet.length; i++) {
            newResultSet[i] = filteredData.get(i);
        }
        return newResultSet;
    }

    private static boolean compare(String a, char op, String b) {
        switch (op) {
            case '=':
                return a.compareTo(b) == 0;
            case '>':
                return a.compareTo(b) > 0;
            case '<':
                return a.compareTo(b) < 0;
            default:
                return false;
        }
    }
}
