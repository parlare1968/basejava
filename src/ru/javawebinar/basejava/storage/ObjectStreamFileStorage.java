package ru.javawebinar.basejava.storage;

import java.io.*;

public class ObjectStreamFileStorage extends AbstractFileStorage {

    public ObjectStreamFileStorage(File directory) {
        super(directory, new ObjectStreamSerializer());
    }
}
