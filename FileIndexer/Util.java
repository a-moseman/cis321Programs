package FileIndexer;

import java.util.ArrayList;

public class Util {
    public static String[] listAsArray(ArrayList<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
