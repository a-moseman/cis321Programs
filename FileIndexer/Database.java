package FileIndexer;

public class Database {
    private Table table;

    public void load(String path) {
        table = Loader.load(path);
    }

    public void save(String path) {
        Writer.save(table, path);
    }

    public String[] select(String headerName) {
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
        String[] result = new String[rows.length];
        for (int i = 0; i < rows.length; i++) {
            result[i] = rows[i][index];
        }
        return result;
    }
}
