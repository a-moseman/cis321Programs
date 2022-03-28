package FileIndexer;

public class Database {
    private Table table;

    public Table getTable() {
        return table;
    }

    public void load(String path) {
        table = Loader.load(path);
    }

    public void save(String path) {
        Writer.save(table, path);
    }

    public void saveBinary(String path) {
        BinaryWriter.save(table, path);
    }
}
