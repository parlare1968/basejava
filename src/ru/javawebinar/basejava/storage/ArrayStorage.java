package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getSearchKey(String uuid) {
        for (var i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void shiftElementsWhenDeleting(int deletedElementIndex) {
        storage[deletedElementIndex] = storage[size - 1];
    }

    @Override
    protected void insertElementWhenSaving(Resume savingElement, int searchKey) {
        storage[size] = savingElement;
    }
}