package com.example.cuentas.util;

import com.example.cuentas.model.Cuenta;
import com.opencsv.*;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvUtils {

    private static final String CSV_FILE = "src/main/resources/cuentas.csv";

    public static List<Cuenta> leerCuentas() throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE))) {
            CsvToBean<Cuenta> csvToBean = new CsvToBeanBuilder<Cuenta>(reader)
                    .withType(Cuenta.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }
    }

    public static void escribirCuentas(List<Cuenta> cuentas) throws IOException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(CSV_FILE))) {
            StatefulBeanToCsv<Cuenta> beanToCsv = new StatefulBeanToCsvBuilder<Cuenta>(writer)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(cuentas);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new IOException("Error al escribir CSV", e);
        }
    }
}
