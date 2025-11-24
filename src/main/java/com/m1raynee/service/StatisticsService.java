package com.m1raynee.service;

import com.m1raynee.model.AddressEntry;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StatisticsService {
    public void printStatistics(List<AddressEntry> entries) {
        printDuplicates(entries);
        printFloorStatistics(entries);
    }

    private void printDuplicates(List<AddressEntry> entries) {
        System.out.println("=== Дублирующиеся записи ===");
        Map<AddressEntry, Long> frequencies =
                entries.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        frequencies.entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .forEach(e -> System.out.println(e.getKey() + " - Повторений: " + e.getValue()));
    }

    private void printFloorStatistics(List<AddressEntry> entries) {
        System.out.println("\n=== Статистика этажности по городам ===");

        // city -> (floor -> count)
        // Используем TreeMap для сортировки городов по алфавиту
        Map<String, Map<Integer, Long>> cityStats =
                entries.stream().collect(Collectors.groupingBy(AddressEntry::city, TreeMap::new,
                        Collectors.groupingBy(AddressEntry::floor, Collectors.counting())));

        cityStats.forEach((city, floors) -> {
            System.out.println("Город: " + city);
            for (int i = 1; i <= 5; i++) {
                long count = floors.getOrDefault(i, 0L);
                System.out.printf("\t%d-этажных зданий: %d%n", i, count);
            }
        });
    }
}
