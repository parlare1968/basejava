package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume with uuid = " + uuid + " is missing in the database");
            return null;
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index < 0) {
            System.out.println("ERROR: resume with uuid = " + r.getUuid() + " is missing in the database");
        } else {
            storage[index] = r;
        }
    }

    public final void delete(String uuid) {
        int index = getSearchKey(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume with uuid = " + uuid + " is missing in the database");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            size--;
            storage[size] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getSearchKey(String uuid);
}
