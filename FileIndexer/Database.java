package FileIndexer;

public class Database {
    private Table table;

    public Table getTable() {
        return table;
    }

    public void loadBinary(String dirPath, String fileName, String indexFileName) {
        table = BinaryReader.load(dirPath, fileName, indexFileName);
    }

    public void saveBinary(String dirPath, String fileName) {
        BinaryWriter.save(table, dirPath, fileName);
    }

    public void initialBinaryLoad(String path) {
        table = BinaryReader.initialLoad(path);
    }
}
