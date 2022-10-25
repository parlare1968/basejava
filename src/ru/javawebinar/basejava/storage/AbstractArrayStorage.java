package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void saveResume(Resume r, int searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Array storage is completely filled", r.getUuid());
        } else {
            insertResume(r,searchKey);
            size++;
        }
    }

    @Override
    protected void updateResume(Resume r, int searchKey) {
        storage[searchKey] = r;
    }

    @Override
    protected Resume getResume(int searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void deleteResume(int searchKey) {
        removeResume(searchKey);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void removeResume(int deletedElementIndex);

    protected abstract void insertResume(Resume savingElement, int searchKey);
}
