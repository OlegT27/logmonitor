package org.company.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.company.beans.InputFileRow;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvReader {

    public List<InputFileRow> readData(String filePath) {
        List<InputFileRow> beans = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            beans = new CsvToBeanBuilder(reader)
                    .withType(InputFileRow.class)
                    .build()
                    .parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return beans;
    }
}
