package org.company.monitor;

import org.company.beans.InputFileRow;
import org.company.beans.OutputBean;
import org.company.beans.UserActivity;
import org.company.csv.CsvReader;
import org.company.csv.CsvWriter;
import org.company.util.InputDataPreparation;
import org.company.util.OutputDataPreparation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ThreadTask implements Runnable {

    private String inputPath;
    private String outputPath;

    public ThreadTask(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    @Override
    public void run() {
        List<InputFileRow> rawData = new CsvReader().readData(inputPath);

        Map<String, UserActivity> activityMap = InputDataPreparation.mergeLinks(rawData);

        for (String key : activityMap.keySet()) {
            UserActivity userActivity = OutputDataPreparation.countAverageDuration(activityMap.get(key));
            activityMap.put(userActivity.getUserID(), userActivity);
        }

        TreeMap<LocalDate, List<OutputBean>> outputMap = new TreeMap(OutputDataPreparation.mapToOutput(activityMap));
        CsvWriter writer = null;

        try {
            writer = new CsvWriter(outputPath, outputMap);
            writer.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.shutdown();
        }


    }
}
