package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListStorageTest {

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }

    protected final Storage storage = new ListStorage();

    @BeforeEach
    void setUp() {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void update() {
        storage.save(RESUME_4);
        RESUME_4 = new Resume(UUID_4);
        storage.update(RESUME_4);
        Assertions.assertSame(storage.get(UUID_4), RESUME_4);
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
    }

    @Test
    void get() {

    }

    @Test
    void delete() {
    }

    @Test
    void getIndex() {
    }

    void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    void updateNotExist(Resume resume) {
        Assertions.assertThrows(NotExistStorageException.class, ()-> storage.update(resume));
    }
}