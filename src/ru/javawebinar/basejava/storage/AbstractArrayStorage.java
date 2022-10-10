package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void save(Resume r) {
        int searchKey = getSearchKey(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: array storage is completely filled");
        } else if (searchKey >= 0) {
            System.out.println("ERROR: resume with uuid = " + r.getUuid() + " is present in the database");
        } else {
            insertResume(r,searchKey);
            size++;
        }
    }

    public final void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index < 0) {
            System.out.println("ERROR: resume with uuid = " + r.getUuid() + " is missing in the database");
        } else {
            storage[index] = r;
        }
    }

    public final Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume with uuid = " + uuid + " is missing in the database");
            return null;
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getSearchKey(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume with uuid = " + uuid + " is missing in the database");
        } else {
            removeResume(index);
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getSearchKey(String uuid);

    protected abstract void removeResume(int deletedElementIndex);

    protected abstract void insertResume(Resume savingElement, int searchKey);
}
