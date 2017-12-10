package org.company.monitor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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


    public void writeProp() {

        properties.setProperty("inputPath", "D://in");
        properties.setProperty("outputPath", "D://out");
        try (OutputStream out = new FileOutputStream("config.properties")) {
            properties.store(out,"paths");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void readProp() {

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
