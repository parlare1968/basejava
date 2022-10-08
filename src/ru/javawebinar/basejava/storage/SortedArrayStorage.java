package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    public void save(Resume r) {
        int searchKey = getSearchKey(r.getUuid());
        if (size == storage.length) {
            System.out.println("ERROR: array storage is completely filled");
        } else if (searchKey >= 0) {
            System.out.println("ERROR: resume with uuid = " + r.getUuid() + " is present in the database");
        } else {
            int index = Math.abs(searchKey) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
        }
    }
}
