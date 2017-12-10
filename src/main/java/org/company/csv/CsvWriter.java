package org.company.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.company.beans.OutputBean;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class CsvWriter {
    private String writerPath;
    private TreeMap<LocalDate, List<OutputBean>> data;
    private Writer writer;

    public CsvWriter (String path, TreeMap<LocalDate, List<OutputBean>> data) throws IOException {
        this.writerPath = path;
        this.writer = new FileWriter(writerPath);
        this.data = data;
    }

    private void writeBean(List<OutputBean> beans) {
        try {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            Collections.sort(beans);
            beanToCsv.write(beans);
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    private void writeSeparator(LocalDate date) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        writer.write(date.format(formatter) + "\n");
    }

    public void writeData() {
        try {
            for (LocalDate key : data.keySet()) {
                writeSeparator(key);
                writeBean(data.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void shutdown()
    {
        try {
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

