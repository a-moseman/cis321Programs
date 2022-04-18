package FileIndexer;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class TableCache {
    private Hashtable<String, Entry> headerCache;
    private final int MAX_HEADER_CACHE_SIZE = 8;

    public TableCache() {
        headerCache = new Hashtable<>();
    }

    public void addToHeaderCache(String headerField, String[][] resultSet) {
        Entry entry = new Entry(headerField, resultSet);
        headerCache.put(headerField, entry);
        manageHeaderCache();
    }

    public String[][] getFromHeaderCache(String headerField) {
        return headerCache.get(headerField).RESULT_SET;
    }

    private void manageHeaderCache() {
        List<Entry> entries;
        if (headerCache.size() > MAX_HEADER_CACHE_SIZE) {
            entries = (List<Entry>) headerCache.values();
            Collections.sort(entries);
            while (headerCache.size() > MAX_HEADER_CACHE_SIZE) {
                headerCache.remove(entries.get(0).KEY);
            }
        }
    }

    class Entry implements Comparable<Entry> {
        public final String KEY;
        public final String[][] RESULT_SET;
        public final long TIME_STAMP;

        public Entry(String key, String[][] resultSet) {
            this.KEY = key;
            this.RESULT_SET = resultSet;
            this.TIME_STAMP = System.currentTimeMillis();
        }

        @Override
        public int compareTo(Entry other) {
            return (int) (TIME_STAMP - other.TIME_STAMP);
        }
    }
}
