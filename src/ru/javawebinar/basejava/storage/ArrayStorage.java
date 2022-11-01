package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void removeResume(int deletedElementIndex) {
        storage[deletedElementIndex] = storage[size - 1];
    }

    @Override
    protected void insertResume(Resume savingElement, int searchKey) {
        storage[size] = savingElement;
    }
}