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

    public Query filter(String headerName, char operator, String toCompareTo) throws Exception {
        if (headerName.equals("name") && operator == '=') {
            resultSet = table.getByName(toCompareTo);
            return this;
        }

        int headerIndex = -1;
        for (int i = 0; i < table.getHeader().length; i++) {
            if (table.getHeader()[i].equals(headerName)) {
                headerIndex = i;
                break;
            }
        }
        if (headerIndex < 0) {
            throw new Exception("Header name not found.");
        }

        ArrayList<String[]> resultList = new ArrayList<>();
        for (String[] row : resultSet) {
            if (compare(row[headerIndex], operator, toCompareTo)) {
                resultList.add(row);
            }
        }
        String[][] newResultSet = new String[resultList.size()][table.getRows()[0].length];
        for (int i = 0; i < newResultSet.length; i++) {
            newResultSet[i] = resultList.get(i);
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
