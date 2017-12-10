package org.company.monitor;

import java.io.File;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class PathsProducer implements Runnable, Observer {
    private BlockingQueue<String> drop;
    private Queue<String> messages = new LinkedList<>(getFiles("D://in"));
    private DirectoryWatcher watcher;


    public PathsProducer(BlockingQueue<String> d, DirectoryWatcher watcher) {
        this.drop = d;
        this.watcher = watcher;
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
        LinkedList<String> changedFiles = (LinkedList<String>) arg;
        this.messages.addAll(changedFiles);
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
