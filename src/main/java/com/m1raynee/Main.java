package com.m1raynee;

import com.m1raynee.model.AddressEntry;
import com.m1raynee.parsing.*;
import com.m1raynee.service.StatisticsService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StatisticsService statsService = new StatisticsService();

        System.out.println("Введите путь до файла (XML/CSV) или 'exit' для выхода.");

        while (true) {
            System.out.print("\nВведите путь к файлу: ");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Завершение работы.");
                break;
            }

            Path path = Paths.get(input);

            if (!Files.exists(path)) {
                System.out.println("Ошибка: Файл не найден. Попробуйте снова.");
                continue;
            }

            FileParser parser = getParserByExtension(path);
            if (parser == null) {
                System.out.println(
                        "Ошибка: Неподдерживаемый формат файла. Используйте .csv или .xml");
                continue;
            }

            try {
                long startTime = System.currentTimeMillis();

                List<AddressEntry> entries = parser.parse(path);
                statsService.printStatistics(entries);

                long endTime = System.currentTimeMillis();
                System.out.println("\nВремя обработки файла: " + (endTime - startTime) + " мс");

            } catch (Exception e) {
                System.out.println("Ошибка при обработке файла: " + e.getMessage());
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    private static FileParser getParserByExtension(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        if (fileName.endsWith(".xml")) {
            return new XmlParser();
        } else if (fileName.endsWith(".csv")) {
            return new CsvParser();
        }
        return null;
    }
}