package com.checkr.interviews;

import java.util.*;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class FundingRaised {

    static Filters filterAction = new Filters();
  

    public static Map<String, String> CsvRowToMap(int i, List<String[]> csvData) {
        String[] KeyFilters = { "permalink", "company_name", "number_employees", "category", "city", "state",
                "funded_date", "raised_amount", "raised_currency", "round" };
        Map<String, String> mapped = new HashMap<String, String>();
        for (int j = 0; j < 10; j++) {
            mapped.put(KeyFilters[j], csvData.get(i)[j]);
        }
        return mapped;
    }

    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> csvData = new ArrayList<String[]>();
        CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"));
        String[] row = null;
        while ((row = reader.readNext()) != null) {
            csvData.add(row);
        }

        reader.close();
        csvData.remove(0);

        if (options.containsKey("company_name")) {
            csvData = filterAction.FilterByKey(options.get("company_name"), 1, csvData);
        }

        if (options.containsKey("city")) {
            csvData = filterAction.FilterByKey(options.get("city"), 4, csvData);
        }

        if (options.containsKey("state")) {
            csvData = filterAction.FilterByKey(options.get("state"), 5, csvData);
        }

        if (options.containsKey("round")) {
            csvData = filterAction.FilterByKey(options.get("round"), 9, csvData);
        }

        List<Map<String, String>> output = new ArrayList<Map<String, String>>();
        for (int i = 0; i < csvData.size(); i++) {
            Map<String, String> mapped = CsvRowToMap(i, csvData);
            output.add(mapped);
        }
        return output;
    }

    public static boolean rowMatchesFilters(Map<String, String> row, Map<String, String> options) {
        return ((!options.containsKey("company_name") || row.get("company_name").equals(options.get("company_name")))
                && (!options.containsKey("city") || row.get("city").equals(options.get("city")))
                && (!options.containsKey("state") || row.get("state").equals(options.get("state")))
                && (!options.containsKey("round") || row.get("round").equals(options.get("round"))));
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = new ArrayList<String[]>();
        CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"));
        String[] row = null;

        while ((row = reader.readNext()) != null) {
            csvData.add(row);
        }

        reader.close();
        csvData.remove(0);
        Map<String, String> mapped = new HashMap<String, String>();
        String headers [] = {"company_name","city","state","round"};
        int positions [] = {1,4,5,9};

        for (int i = 0; i < csvData.size(); i++) {

            if(rowMatchesFilters(CsvRowToMap(i, csvData), options)){
                return CsvRowToMap(i, csvData);
            }
        }
        throw new NoSuchEntryException();
    }

    public static void main(String[] args) {
        try {
            Map<String, String> options = new HashMap<String, String>();
            options.put("company_name", "Facebook");
            options.put("round", "a");
            System.out.print(FundingRaised.where(options).size());
        } catch (IOException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }
}

class NoSuchEntryException extends Exception {
}
