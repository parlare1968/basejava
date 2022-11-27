package ru.javawebinar.basejava.storage;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    public ObjectStreamPathStorage(String dir) {
        super(dir, new ObjectStreamSerializer());
    }
}
