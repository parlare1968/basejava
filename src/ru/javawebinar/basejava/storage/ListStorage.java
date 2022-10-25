package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveResume(Resume r, int searchKey) {
        storage.add(r);
    }

    @Override
    protected void updateResume(Resume r, int searchKey) {
        storage.set(searchKey, r);
    }

    @Override
    protected Resume getResume(int searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void deleteResume(int searchKey) {
        storage.remove(searchKey);
    }
}
