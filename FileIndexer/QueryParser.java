package FileIndexer;

public class QueryParser {
    public static String[][] parse(Database db, String query) {
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
        return q.getResultSet();
    }
}
