package com.animalbatlle.utils;

import com.animalbattle.entities.Animal;

import java.io.*;
import java.util.Map;

public class InputOutput {

    private InputOutput() {
    }

    public static String writeResultsToFile(Map<Animal, Integer> championshipTable) throws IOException {

        System.out.println("Writing results");
        String path = "src/main/resources/results.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry<Animal, Integer> result : championshipTable.entrySet()) {
                writer.write(String.format("%s: %s victories %n", result.getKey().getFullName(), result.getValue()));
            }
        }
        return path;
    }

    public static void serialiseFighter(Animal fighter) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/animal.obj");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(fighter);
        }
    }

    public static Animal deserializeFighter() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/animal.obj");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Animal) objectInputStream.readObject();
        }
    }
}
