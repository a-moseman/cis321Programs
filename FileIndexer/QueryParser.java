package FileIndexer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class QueryParser {
    private Database db;
    private Hashtable<String, CacheEntry> cache;

    public QueryParser(Database db) {
        this.db = db;
        this.cache = new Hashtable<>();
    }

    private void manageCache() {
        List<CacheEntry> list = new ArrayList<CacheEntry>(cache.values());
        ArrayList<String> keysToRemove = new ArrayList<>();
        Collections.sort(list);
        while (list.size() > 10) {
            keysToRemove.add(list.get(0).QUERY);
            list.remove(0);
        }
        for (String key : keysToRemove) {
            cache.remove(key);
        }
    }

    public String[][] parse(String query) {
        // TODO: fix issue with multiple queries
        // TODO: need to unload files after query also
        // cache shortcut
        if (cache.containsKey(query)) {
            return cache.get(query).RESULT_SET;
        }
        String[] parts = Util.split(query, ' ', false);
        String headerName = parts[0].trim();
        char operator = parts[1].trim().charAt(0);
        String toCompareTo = parts[2].trim();
        try {
            return Query.query(db, headerName, operator, toCompareTo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        /*
         * // parse and run query
         * String[][] results = db.getTable().getRows();
         * 
         * String[] subqueries = query.split("&&");
         * for (String subquery : subqueries) {
         * subquery = subquery.trim();
         * String[] parts = Util.split(subquery, ' ', false);
         * String headerName = parts[0].trim();
         * char operator = parts[1].trim().charAt(0);
         * String toCompareTo = parts[2].trim();
         * try {
         * results = Query.query(db, headerName, operator, toCompareTo);
         * } catch (Exception e) {
         * e.printStackTrace();
         * return null;
         * }
         * }
         * cache.put(query, new CacheEntry(query, results));
         * manageCache();
         * return results;
         */
    }
}
