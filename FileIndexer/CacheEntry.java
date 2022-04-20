package FileIndexer;

public class CacheEntry implements Comparable<CacheEntry> {
    public final String QUERY;
    public final String[][] RESULT_SET;
    public final long TIMESTAMP;

    public CacheEntry(String query, String[][] resultSet) {
        this.QUERY = query;
        this.RESULT_SET = resultSet;
        this.TIMESTAMP = System.currentTimeMillis();
    }

    @Override
    public int compareTo(CacheEntry other) {
        return (int) (TIMESTAMP - other.TIMESTAMP);
    }
}