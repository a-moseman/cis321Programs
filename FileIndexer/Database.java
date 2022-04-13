package FileIndexer;

public class Database {
    private Table table;

    public Table getTable() {
        return table;
    }

    public void load(String path) {
        table = Reader.load(path);
    }

    public void loadBinary(String path) {
        table = BinaryReader.initialLoad(path);
    }

    public void save(String path) {
        Writer.save(table, path);
    }

    public void saveBinary(String path) {
        BinaryWriter.save(table, path);
    }
}
