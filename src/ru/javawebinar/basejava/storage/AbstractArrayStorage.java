package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (index == -1) {
            System.out.println("ERROR: resume with uuid = " + uuid + " is missing in the database");
            return null;
        }
        return storage[index];
    }

    protected abstract int getSearchKey(String uuid);
}
