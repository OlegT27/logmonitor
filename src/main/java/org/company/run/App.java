package org.company.run;

import org.company.monitor.DirectoryWatcher;
import org.company.monitor.PathsConsumer;
import org.company.monitor.PathsProducer;
import org.company.monitor.PropertyLoader;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {

    public static void main(String[] args) throws IOException {
        Properties properties = new PropertyLoader().getProperties();
        BlockingQueue<String> drop = new LinkedBlockingQueue();
        DirectoryWatcher dw = new DirectoryWatcher(properties.getProperty("inputPath"));
        (new Thread(dw)).start();
        (new Thread(new PathsProducer(drop, dw))).start();
        (new Thread(new PathsConsumer(drop))).start();
    }
}
