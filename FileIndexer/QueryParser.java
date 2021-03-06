package FileIndexer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 * Class to parse a String as a query.
 */
public class QueryParser {
    private Database db;
    private Hashtable<String, CacheEntry> cache;

    /**
     * Construct a QueryParser.
     * 
     * @param db The database.
     */
    public QueryParser(Database db) {
        this.db = db;
        this.cache = new Hashtable<>();
    }

    /**
     * Manage the cache of the QueryParser.
     */
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

    /**
     * Parse the provided query.
     * 
     * @param query The query.
     * @return String[][]
     */
    public String[][] parse(String query) {
        try {
            String[] subqueries = query.split("&&");
            // do initial query condition
            String[] parts = Util.split(subqueries[0].trim(), ' ', false);
            String headerName = parts[0].trim();
            char operator = parts[1].trim().charAt(0);
            String toCompareTo = parts[2].trim();
            String[][] results = Query.initialQuery(db, headerName, operator, toCompareTo);
            for (int i = 1; i < subqueries.length; i++) {
                String subquery = subqueries[i].trim();
                parts = Util.split(subquery, ' ', false);
                headerName = parts[0].trim();
                operator = parts[1].trim().charAt(0);
                toCompareTo = parts[2].trim();
                results = Query.query(results, db, headerName, operator, toCompareTo);
            }
            cache.put(query, new CacheEntry(query, results));
            manageCache();
            return results;
        } catch (Exception e) {
            System.out.println("Invalid query.");
            return null;
        }
    }
}
