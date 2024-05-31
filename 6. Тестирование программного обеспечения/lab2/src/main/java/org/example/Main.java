package org.example;

import org.example.log.LogarithmicFunctionCalculator;
import org.example.trig.*;
import org.example.trig.TrigonometricFunctionCalculator;

import java.io.File;

import static java.lang.Math.PI;

public class Main {
    public static void main(String[] args) {

//        String directoryPath = "src/main/resources/logs/log/";
//        File directory = new File(directoryPath);
//        directory.mkdirs();
//
//        CsvFilePrinter sinLogger = new CsvFilePrinter("src/main/resources/logs/log/sin.csv");
//        CsvFilePrinter cosLogger = new CsvFilePrinter("src/main/resources/logs/log/cos.csv");
//        CsvFilePrinter tanLogger = new CsvFilePrinter("src/main/resources/logs/log/tan.csv");
//        CsvFilePrinter cotLogger = new CsvFilePrinter("src/main/resources/logs/log/cot.csv");
//        CsvFilePrinter secLogger = new CsvFilePrinter("src/main/resources/logs/log/sec.csv");
//        CsvFilePrinter cscLogger = new CsvFilePrinter("src/main/resources/logs/log/csc.csv");
//        CsvFilePrinter trigLogger = new CsvFilePrinter("src/main/resources/logs/log/trig.csv");
//
//        for (int i = 1; i <= 20; i += 1){
//            double e = PI * (i/20.0) - (PI/2);
//            try {
//                sinLogger.log(e, new Sin().calculateValue(e, 1e-9));
//            } catch (Exception ignored) {
//
//            }
//            try {
//                cosLogger.log(e, new Cos().calculateValue(e, 1e-9));
//            } catch (Exception ignored) {
//
//            }
//            try {
//                tanLogger.log(e, new Tan().calculateValue(e, 1e-9));
//            } catch (Exception ignored) {
//
//            }
//            try {
//                cotLogger.log(e, new Cot().calculateValue(e, 1e-9));
//            }catch (Exception ignored) {
//
//            }
//            try {
//                secLogger.log(e, new Sec().calculateValue(e, 1e-9));
//            }catch (Exception ignored) {
//
//            }
//            try{
//                cscLogger.log(e, new Csc().calculateValue(e, 1e-9));
//            }catch (Exception ignored) {
//
//            }
//            try {
//                trigLogger.log(e, new TrigonometricFunctionCalculator().calculateValue(e, 1e-9));
//            }catch (Exception ignored) {
//
//            }
//
//        }

        for (int i = 1; i <= 20; i += 1){
            System.out.printf("%s, %s\n", i*0.01, new LogarithmicFunctionCalculator().calculateValue(i*0.01, 1e-9));
        }

    }
}