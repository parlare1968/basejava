package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canWrite() || directory.canRead()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO error", r.getUuid(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("Write resume error", r.getUuid(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("Read resume error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File was not deleted", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> copyToList() {
        File[] listFiles = directory.listFiles();
        checkDirectory(listFiles);
        List<Resume> list = new ArrayList<>();
        for (File file : listFiles) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        checkDirectory(list);
        for (File file : list) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        checkDirectory(list);
        return list.length;
    }

    private void checkDirectory(Object list) {
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
    }
}
