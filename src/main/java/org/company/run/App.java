package org.company.run;

import com.oracle.jrockit.jfr.Producer;
import org.company.monitor.DirectoryWatcher;
import org.company.monitor.PathsConsumer;
import org.company.monitor.PathsProducer;
import org.company.monitor.ThreadTask;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.function.Consumer;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {

        BlockingQueue<String> drop = new SynchronousQueue<String>();
        DirectoryWatcher dw = new DirectoryWatcher("D://in");
        (new Thread(dw)).start();
        (new Thread(new PathsProducer(drop, dw))).start();
        (new Thread(new PathsConsumer(drop))).start();
        /*ThreadTask t = new ThreadTask("D://in//file1.csv", "D:///out//1.csv");
        t.run();*/
    }
}
