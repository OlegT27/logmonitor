package org.company.monitor;

import java.io.File;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class PathsProducer implements Runnable, Observer {
    private final BlockingQueue<String> drop;
    private Queue<String> messages;
    private DirectoryWatcher watcher;


    public PathsProducer(BlockingQueue<String> d, DirectoryWatcher watcher) {
        this.drop = d;
        this.watcher = watcher;
        Properties properties = new PropertyLoader().getProperties();
        this.messages = new LinkedList<>(getFiles(properties.getProperty("inputPath")));
    }

    @Override
    public void run() {
        watcher.addObserver(this);
        while (true) {
            while (!messages.isEmpty()) {
                try {
                    drop.put(messages.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            for (String s : ((List<String>) arg)) {
                drop.put(s);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private List<String> getFiles(String path) {
        File myFolder = new File(path);
        List<String> paths = new ArrayList<>();
        List<File> files = Arrays.stream(myFolder.listFiles()).filter(File::isFile).collect(Collectors.toList());
        for (File f :
                files) {
            paths.add(f.getName());
        }
        return paths;
    }
}
