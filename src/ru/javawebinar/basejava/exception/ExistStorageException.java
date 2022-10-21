package ru.javawebinar.basejava.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("Resume with uuid = " + uuid + " is present in the database", uuid);
    }
}