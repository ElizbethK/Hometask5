package com.netcracker.util;

import com.netcracker.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVHandler {

    private static final String CSV_PATH = "./csv.csv";

    public static void acceptPerson(boolean append, String[] csv) {
        try(FileWriter writer = new FileWriter(CSV_PATH, append);
            CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(csv);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Person findThisPerson(Person person) {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_PATH));
             CSVReader csvReader = new CSVReader(reader);) {

            String[] currentPerson;


            while ((currentPerson = csvReader.readNext()) != null) {
                if (person.getSurname().equalsIgnoreCase(currentPerson[0])) {
                    if (person.getName().equalsIgnoreCase(currentPerson[1])) {
                        person.setSurname(currentPerson[0]);
                        person.setName(currentPerson[1]);
                        person.setPatronymic((currentPerson[2]));
                        person.setAge(Integer.parseInt(currentPerson[3]));
                        person.setSalary(Integer.parseInt(currentPerson[4]));
                        person.setEmail(currentPerson[5]);
                        person.setJob(currentPerson[6]);
                        return person;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getExtension(String file) {
        String extension = "";
        int i = file.lastIndexOf('.');

        if (i > 0) {
            extension = file.substring(i + 1);
        }
        return extension;
    }
}
