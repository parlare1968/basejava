package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    public static final String UUID_NOT_EXIST = UUID.randomUUID().toString();
    public static final Resume RESUME_NOT_EXIST = ResumeTestData.createResume(UUID_NOT_EXIST, "dummy");

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final Resume RESUME_1 = ResumeTestData.createResume(UUID_1, "fullName1");

    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final Resume RESUME_2 = ResumeTestData.createResume(UUID_2, "fullName2");

    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final Resume RESUME_3 = ResumeTestData.createResume(UUID_3, "fullName3");

    private static final String UUID_TO_SAVE = UUID.randomUUID().toString();
    public static final Resume RESUME_TO_SAVE = ResumeTestData.createResume(UUID_TO_SAVE, "toSave");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_TO_SAVE);
        assertGet(RESUME_TO_SAVE);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_1, "newFullName");
        storage.update(r);
        assertSameResume(r);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void getAllSorted() {
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        List<Resume> actual = storage.getAllSorted();
        assertEquals(expected, actual);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int expected) {
        assertEquals(expected, storage.size());
    }

    private void assertGet(Resume expected) {
        assertEquals(expected, storage.get(expected.getUuid()));
    }

    private void assertSameResume(Resume expected) {
        assertEquals(expected, storage.get(expected.getUuid()));
    }
}