package FileIndexer;

import java.util.ArrayList;

/**
 * Class representing a query.
 */
public class Query {
    private static final int ROWS_PER_FILE = 100;

    /**
     * Make a query, querying from the data of the previous query.
     * 
     * @param data        The previous query's results.
     * @param db          The database.
     * @param headerName  The name of the header to filter by.
     * @param operator    The operator of the condition.
     * @param toCompareTo The value to compare to.
     * @return String[][]
     * @throws Exception
     */
    public static String[][] query(String[][] data, Database db, String headerName, char operator, String toCompareTo)
            throws Exception {
        if (headerName.equals("name")) {
            loadQueriedTables(db, operator, toCompareTo);
        } else {
            db.loadAll();
        }
        int headerIndex = searchHeaderIndexByName(db.getTable().getHeader(), headerName);
        return convertTo2DArray(filterDataByCondition(data, headerIndex, operator, toCompareTo));
    }

    /**
     * Convert an ArrayList of String arrays to a 2D String matrix.
     * 
     * @param list The ArrayList to convert.
     * @return String[][]
     */
    private static String[][] convertTo2DArray(ArrayList<String[]> list) {
        String[][] output = new String[list.size()][list.get(0).length];
        for (int i = 0; i < list.size(); i++) {
            output[i] = list.get(i);
        }
        return output;
    }

    /**
     * Make the initial query of the overall query.
     * 
     * @param db          The database.
     * @param headerName  The header name to filter by.
     * @param operator    The operator of the condition.
     * @param toCompareTo The value to compare to.
     * @return String[][]
     * @throws Exception
     */
    public static String[][] initialQuery(Database db, String headerName, char operator, String toCompareTo)
            throws Exception {
        db.loadAll();
        // perform the query
        if (headerName.equals("name")) {
            return query(db, operator, toCompareTo);
        }
        return filter(db.getTable(), headerName, operator, toCompareTo);
    }

    /**
     * Load all tables required by the query.
     * 
     * @param db          The database.
     * @param operator    The operator of the condition.
     * @param toCompareTo The value to compare to.
     */
    private static void loadQueriedTables(Database db, char operator, String toCompareTo) {
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
    }

    private static String[][] query(Database db, char operator, String toCompareTo) throws Exception {
        loadQueriedTables(db, operator, toCompareTo);
        return filter(db.getTable(), "name", operator, toCompareTo);
    }

    /**
     * Search the header for a matching name and return the index.
     * Returns -1 if the name is missing.
     * 
     * @param headerName
     * @return int
     */
    private static int searchHeaderIndexByName(String[] header, String headerName) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(headerName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Filter the provided data by the provided condition.
     * 
     * @param data        The data.
     * @param headerIndex The index of the value to compare.
     * @param operator    The operator of the condition.
     * @param toCompareTo The value to compare to.
     * @return
     */
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

    /**
     * Filter the provided data by the provided condition.
     * 
     * @param table       The table.
     * @param headerName  The header name of filter by.
     * @param operator    The operator of the condition.
     * @param toCompareTo The value to compare to.
     * @return
     */
    private static String[][] filter(Table table, String headerName, char operator, String toCompareTo)
            throws Exception {
        if (headerName.equals("name") && operator == '=') {
            return table.getByName(toCompareTo);
        }
        int headerIndex = searchHeaderIndexByName(table.getHeader(), headerName);
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

    /**
     * Compare a String to another.
     * 
     * @param a  The String to compare.
     * @param op The operator of the comparison.
     * @param b  The String to compare to.
     * @return boolean
     */
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
