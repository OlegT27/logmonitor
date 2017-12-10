package org.company.monitor;

import java.io.*;
import java.util.Properties;

public class PropertyLoader {

    private Properties properties;

    public PropertyLoader() {
        properties = new Properties();
    }

    public Properties getProperties() {
        readProp();
        return properties;
    }


    private void writeProp() {

        properties.setProperty("inputPath", "D://in");
        properties.setProperty("outputPath", "D://out");
        try (OutputStream out = new FileOutputStream("config.properties")) {
            properties.store(out, "paths");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readProp() {
        try (InputStream in = new FileInputStream("config.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
