package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage<SK> extends AbstractStorage<SK> {
    protected final Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected void doSave(Resume r, SK searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Resume r, SK searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected boolean isExist(SK searchKey) {
        return searchKey != null;
    }

    @Override
    protected List<Resume> copyToList() {
        return new ArrayList<>(map.values());
    }
}