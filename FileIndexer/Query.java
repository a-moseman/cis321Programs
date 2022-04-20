package FileIndexer;

import java.util.ArrayList;

public class Query {
    private Table table;
    private String[][] resultSet;

    public Query(Table table) {
        this.table = table;
        this.resultSet = table.getRows();
    }

    public String[][] getResultSet() {
        return resultSet;
    }

    /**
     * Search the header for a matching name and return the index.
     * Returns -1 if the name is missing.
     * 
     * @param headerName
     * @return int
     */
    private int searchHeaderIndexByName(String headerName) {
        for (int i = 0; i < table.getHeader().length; i++) {
            if (table.getHeader()[i].equals(headerName)) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<String[]> filterDataByCondition(int headerIndex, char operator, String toCompareTo) {
        ArrayList<String[]> resultList = new ArrayList<>();
        for (String[] row : resultSet) {
            if (compare(row[headerIndex], operator, toCompareTo)) {
                resultList.add(row);
            }
        }
        return resultList;
    }

    public Query filter(String headerName, char operator, String toCompareTo) throws Exception {
        // below is a fast way for querying by name
        if (headerName.equals("name") && operator == '=') {
            resultSet = table.getByName(toCompareTo);
            return this;
        }
        int headerIndex = searchHeaderIndexByName(headerName);
        if (headerIndex < 0) {
            throw new Exception("Header name not found.");
        }
        ArrayList<String[]> filteredData = filterDataByCondition(headerIndex, operator, toCompareTo);
        String[][] newResultSet = new String[filteredData.size()][table.getRows()[0].length];
        for (int i = 0; i < newResultSet.length; i++) {
            newResultSet[i] = filteredData.get(i);
        }
        resultSet = newResultSet;
        return this;
    }

    private boolean compare(String a, char op, String b) {
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
