package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getSearchKey(String uuid) {
        for (var i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("ERROR: array storage is completely filled");
        } else if (getSearchKey(r.getUuid()) != -1) {
            System.out.println("ERROR: resume with uuid = " + r.getUuid() + " is present in the database");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index == -1) {
            System.out.println("ERROR: resume with uuid = " + r.getUuid() + " is missing in the database");
        } else {
            storage[index] = r;
        }
    }

    public void delete(String uuid) {
        int index = getSearchKey(uuid);
        if (index == -1) {
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

    public int size() {
        return size;
    }
}