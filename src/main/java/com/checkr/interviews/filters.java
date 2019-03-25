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

    public boolean rowMatchesFilters(Map<String, String> row, Map<String, String> options) {
        return ((!options.containsKey("company_name") || row.get("company_name").equals(options.get("company_name")))
                && (!options.containsKey("city") || row.get("city").equals(options.get("city")))
                && (!options.containsKey("state") || row.get("state").equals(options.get("state")))
                && (!options.containsKey("round") || row.get("round").equals(options.get("round"))));
    }

}