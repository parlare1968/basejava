package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void save(Resume r) {
        int searchKey = getSearchKey(r.getUuid());
        if (searchKey >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveResume(r, searchKey);
        }
    }

    @Override
    public final void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(r, index);
        }
    }

    @Override
    public final Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    @Override
    public final void delete(String uuid) {
        int index = getSearchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
        }
    }

    protected abstract int getSearchKey(String uuid);

    protected abstract void saveResume(Resume r, int searchKey);

    protected abstract void updateResume(Resume r, int searchKey);

    protected abstract Resume getResume(int searchKey);

    protected abstract void deleteResume(int searchKey);
 }
