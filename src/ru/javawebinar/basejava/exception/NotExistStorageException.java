package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume with uuid = " + uuid + " is missing in the database", uuid);
    }
}
