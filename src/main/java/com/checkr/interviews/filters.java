package com.checkr.interviews;
import java.util.*;

public class Filters {
    public List<String[]> FilterByKey(String key, int position, List<String[]> Data) {
        List<String[]> results = new ArrayList<String[]>();
        for (int i = 0; i < Data.size(); i++) {
            if (Data.get(i)[position].equals(key)) {
                results.add(Data.get(i));
            }
        }
        return results;
    }

}