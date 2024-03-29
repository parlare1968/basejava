package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid, "fullName"), Comparator.comparing(Resume::getUuid));
    }

    @Override
    protected void removeResume(int deletedElementIndex) {
        System.arraycopy(storage, deletedElementIndex + 1, storage, deletedElementIndex, size - deletedElementIndex - 1);
    }

    @Override
    protected void insertResume(Resume savingElement, int searchKey) {
        int index = Math.abs(searchKey) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = savingElement;
    }
}
