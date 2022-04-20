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
        if (cache.containsKey(query)) {
            return cache.get(query).RESULT_SET;
        }
        Query q = new Query(db.getTable());
        String[] subqueries = query.split("&&");
        for (String subquery : subqueries) {
            subquery = subquery.trim();
            String[] parts = Util.split(subquery, ' ', false);
            String headerName = parts[0].trim();
            char operator = parts[1].trim().charAt(0);
            String toCompareTo = parts[2].trim();
            try {
                q.filter(headerName, operator, toCompareTo);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        cache.put(query, new CacheEntry(query, q.getResultSet()));
        manageCache();
        return q.getResultSet();
    }
}
