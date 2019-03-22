package com.checkr.interviews;
import java.util.*;

public class filters {
    public static List<String[]> FilterByKey(String key, int position, List<String[]> Data) {
        List<String[]> results = new ArrayList<String[]>();
        for (int i = 0; i < Data.size(); i++) {
            if (Data.get(i)[position].equals(key)) {
                results.add(Data.get(i));
            }
        }
        return results;
    }

    public static Map<String, String> CsvRowToMap(int i, List<String[]> csvData) {
        String[] KeyFilters = { "permalink", "company_name", "number_employees", "category", "city", "state",
                "funded_date", "raised_amount", "raised_currency", "round" };
        Map<String, String> mapped = new HashMap<String, String>();
        for (int j = 0; j < 10; j++) {
            mapped.put(KeyFilters[j], csvData.get(i)[j]);
        }
        return mapped;
    }
}