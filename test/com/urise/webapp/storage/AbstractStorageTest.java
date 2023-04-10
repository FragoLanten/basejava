package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected static final String FULLNAME_1 = "Andrew";
    protected static final String FULLNAME_2 = "Vadim";
    protected static final String FULLNAME_3 = "Natalya";
    protected static final String FULLNAME_4 = "John";

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    protected static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(FULLNAME_1);
        RESUME_2 = new Resume(FULLNAME_2);
        RESUME_3 = new Resume(FULLNAME_3);
        RESUME_4 = new Resume(FULLNAME_4);
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assertions.assertIterableEquals(List.of(), storage.getAllSorted());
    }

    @Test
    void update() {
        storage.update(RESUME_3);
        Assertions.assertEquals(storage.get(RESUME_3.getUuid()), RESUME_3);
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void get() {
        assertGet(RESUME_1);
    }

    @Test
    void delete() {
        storage.delete(RESUME_3.getUuid());
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    void getAllSorted() {
        List<Resume> testList = storage.getAllSorted();
        Assertions.assertEquals(3, testList.size());
        Assertions.assertEquals(testList, Arrays.asList(RESUME_1, RESUME_3, RESUME_2));
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    public void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_4));
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    public void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete("uuid10"));
    }

    public void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    public void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

}
