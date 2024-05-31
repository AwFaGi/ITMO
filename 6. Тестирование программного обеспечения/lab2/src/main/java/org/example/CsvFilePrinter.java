package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class CsvFilePrinter {
    private final String fileName;
    private BufferedWriter writer;

    public CsvFilePrinter(String fileName){
        this.fileName = fileName;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile() throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    public void log(double x, double y) {
        try {
            writer.write(String.format("%s, %s\n", x, y));
            writer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}