package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public class Config {
    protected static final File PROPS = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private final Properties props = new Properties();
    private final File storageDir;

    private Config() {

        try (InputStream is = Files.newInputStream(PROPS.toPath())) {
            // load a properties file
            props.load(is);
            // get the property value and print it out
            storageDir = new File(props.getProperty("storage.dir"));
            System.out.println(props.getProperty("db.url"));
            System.out.println(props.getProperty("db.user"));
            System.out.println(props.getProperty("db.password"));

        } catch (IOException ex) {
            throw new IllegalStateException("invalid config file " + PROPS.getAbsolutePath());
        }

    }

    public File getStorageDir() {
        return storageDir;
    }

    public static Config get() {
        return INSTANCE;
    }
}
