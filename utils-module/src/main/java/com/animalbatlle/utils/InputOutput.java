package com.animalbatlle.utils;

import com.animalbattle.entities.Animal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputOutput {

    private InputOutput() {
    }

    public static List<Animal> readFile() throws IOException {

        System.out.println("Reading fighters");

        return Stream.of(Files.lines(Path.of(enterPath())))
                .flatMap(x -> x)
                .map(CreateFighter::createFighter)
                .collect(Collectors.toList());
    }

    public static String writeResultsToFile(Map<Animal, Integer> championshipTable) throws IOException {

        System.out.println("Writing results");

        String path = "results.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            for (Map.Entry<Animal, Integer> result : championshipTable.entrySet()) {
                writer.write(String.format("%s: %s victories %n", result.getKey().getFullName(), result.getValue()));
            }
        }
        return path;
    }

    public static void serialiseFighter(Animal fighter) throws IOException {

        try (FileOutputStream fileOutputStream = new FileOutputStream("animal.obj");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(fighter);
        }
    }

    public static Animal deserialiseFighter() throws IOException, ClassNotFoundException {

        try (FileInputStream fileInputStream = new FileInputStream("animal.obj");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Animal) objectInputStream.readObject();
        }
    }

    private static String enterPath() throws IOException {

        String path;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Enter path to the txt file");

            path = reader.readLine();

            while (!path.matches(".+\\.txt$")) {

                System.out.println("Invalid path to the txt file. The extension of the file should be txt. Enter again");

                path = reader.readLine();
            }
        }
        return path;
    }
}
