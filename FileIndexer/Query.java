package FileIndexer;

import java.util.ArrayList;

public class Query {
    private Table table;
    private String[] resultSet;

    public Query(Table table) {
        this.table = table;
    }

    public Query select(String headerName) {
        String[] header = table.getHeader();
        String[][] rows = table.getRows();
        int index = -1;
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(headerName)) {
                index = i;
                break;
            }
        }
        // if not in header
        if (index == -1) {
            return null;
        }
        resultSet = new String[rows.length];
        for (int i = 0; i < rows.length; i++) {
            resultSet[i] = rows[i][index];
        }
        return this;
    }

    public Query whereGreaterThan(String toCompareTo) {
        ArrayList<String> list = new ArrayList<>();
        for (String field : resultSet) {
            if (field.compareTo(toCompareTo) > 0) {
                list.add(field);
            }
        }
        resultSet = (String[]) list.toArray();
        return this;
    }

    public Query whereLessThan(String toCompareTo) {
        ArrayList<String> list = new ArrayList<>();
        for (String field : resultSet) {
            if (field.compareTo(toCompareTo) < 0) {
                list.add(field);
            }
        }
        resultSet = (String[]) list.toArray();
        return this;
    }

    public Query whereEqualTo(String toCompareTo) {
        ArrayList<String> list = new ArrayList<>();
        for (String field : resultSet) {
            if (field.compareTo(toCompareTo) == 0) {
                list.add(field);
            }
        }
        resultSet = (String[]) list.toArray();
        return this;
    }
}
