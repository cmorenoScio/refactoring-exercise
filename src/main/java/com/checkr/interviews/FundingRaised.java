package com.checkr.interviews;

import java.util.*;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class FundingRaised {

    static filters obj = new filters();

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
            csvData = obj.FilterByKey(options.get("company_name"), 1, csvData);
        }

        if (options.containsKey("city")) {
            csvData = obj.FilterByKey(options.get("city"), 4, csvData);
        }

        if (options.containsKey("state")) {
            csvData = obj.FilterByKey(options.get("state"), 5, csvData);
        }

        if (options.containsKey("round")) {
            csvData = obj.FilterByKey(options.get("round"), 9, csvData);
        }

        List<Map<String, String>> output = new ArrayList<Map<String, String>>();
        for (int i = 0; i < csvData.size(); i++) {
            Map<String, String> mapped = obj.CsvRowToMap(i, csvData);
            output.add(mapped);
        }
        return output;
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

            for(int j=0; j <headers.length;j++ ){
                if(options.containsKey(headers[j])){
                    if (csvData.get(i)[positions[j]].equals(options.get(headers[j]))) {
                        mapped = obj.CsvRowToMap(i, csvData);
                    } else {
                        continue;
                    }
                }
            }
            if (options.containsKey("company_name")) {
                if (csvData.get(i)[1].equals(options.get("company_name"))) {
                    mapped = obj.CsvRowToMap(i, csvData);
                } else {
                    continue;
                }
            }

            if (options.containsKey("city")) {
                if (csvData.get(i)[4].equals(options.get("city"))) {
                    mapped = obj.CsvRowToMap(i, csvData);
                } else {
                    continue;
                }
            }

            if (options.containsKey("state")) {
                if (csvData.get(i)[5].equals(options.get("state"))) {
                    mapped = obj.CsvRowToMap(i, csvData);
                } else {
                    continue;
                }
            }

            if (options.containsKey("round")) {
                if (csvData.get(i)[9].equals(options.get("round"))) {
                    mapped = obj.CsvRowToMap(i, csvData);
                } else {
                    continue;
                }
            }
            return mapped;
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
