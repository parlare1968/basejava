package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializer.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final Serializer serializer;

    public FileStorage(File directory, Serializer serializer) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canWrite() || !directory.canRead()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.serializer = serializer;
        this.directory = directory;
    }

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
            serializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Write resume error", r.getUuid(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return serializer.doRead(new BufferedInputStream(new FileInputStream(file)));
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
        File[] listFiles = getListFiles();
        List<Resume> list = new ArrayList<>();
        for (File file : listFiles) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] list = getListFiles();
        for (File file : list) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        File[] list = getListFiles();
        return list.length;
    }

    private File[] getListFiles() {
        File[] listFiles = directory.listFiles();
        if (listFiles == null) {
            throw new StorageException("I/O Error", null);
        }
        return listFiles;
    }
}
