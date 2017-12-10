package org.company.monitor;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PathsConsumer implements Runnable {
    private final BlockingQueue<String> drop;
    private String inputFolder;
    private String outputFolder;

    public PathsConsumer(BlockingQueue<String> d) {
        this.drop = d;
        Properties property = new PropertyLoader().getProperties();
        inputFolder = property.getProperty("inputPath");
        outputFolder = property.getProperty("outputPath");
    }

    public void run() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        while (true) {
            String fileName = null;
            try {
                fileName = drop.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPool.submit(new ThreadTask
                        (inputFolder+"//"+fileName,outputFolder+"//avg_"+fileName));
        }
    }
}



