package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedArrayStorageTest extends AbstractArrayStorageTest {
    private final SortedArrayStorage storage = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void getIndex() {
        Assertions.assertEquals(0, storage.getIndex(UUID_1));
    }

    @Test
    void insertResume() {
        Resume resume5 = new Resume("uuid5");
        storage.save(resume5);
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);

        final Resume[] testStorage = new Resume[5];
        testStorage[0] = new Resume(UUID_1);
        testStorage[1] = new Resume(UUID_2);
        testStorage[2] = new Resume(UUID_3);
        testStorage[3] = resume4;
        testStorage[4] = resume5;
        Assertions.assertArrayEquals(testStorage, storage.getAll());
    }

    @Test
    void removeResume() {
//        Resume resume5 = new Resume("uuid5");
//        storage.save(resume5);
//        Resume resume4 = new Resume("uuid4");
//        storage.save(resume4);
//        storage.removeResume(3);
//
//        final Resume[] testStorage = new Resume[5];
//        testStorage[0] = new Resume(UUID_1);
//        testStorage[1] = new Resume(UUID_2);
//        testStorage[2] = new Resume(UUID_3);
//        testStorage[3] = resume5;
//        Assertions.assertArrayEquals(testStorage, storage.getAll());
    }
}