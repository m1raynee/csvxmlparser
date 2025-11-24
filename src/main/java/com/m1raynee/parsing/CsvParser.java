package com.m1raynee.parsing;

import com.m1raynee.model.AddressEntry;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvParser implements FileParser {
    @Override
    public List<AddressEntry> parse(Path path) throws Exception {
        List<AddressEntry> entries = new ArrayList<>();
        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {
            // Skip header
            if (line.isBlank() || line.contains("\"sity\"")) continue;

            String[] parts = line.split(";");
            if (parts.length < 4) continue;

            String city = clean(parts[0]);
            String street = clean(parts[1]);
            String house = clean(parts[2]);
            int floor = Integer.parseInt(clean(parts[3]));

            entries.add(new AddressEntry(city, street, house, floor));
        }
        return entries;
    }

    private String clean(String input) {
        return input.replace("\"", "").trim();
    }
}
