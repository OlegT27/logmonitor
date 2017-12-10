package org.company.monitor;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class DirectoryWatcher extends Observable implements Runnable {
    private WatchService watchService;
    private Path path;

    public DirectoryWatcher(String path) throws IOException {
        this.path = Paths.get(path);
        this.watchService = FileSystems.getDefault().newWatchService();
        this.path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY);
    }

    public void startMonitor() {
        WatchKey key;
        List<String> files = new ArrayList<>();
        try {
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    files.add(event.context().toString());
                }
                setChanged();
                notifyObservers(files);
                key.reset();
                files.clear();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        startMonitor();
    }
}
