package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStorageTest {

    private final ArrayStorage storage = new ArrayStorage();

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
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
        Assertions.assertEquals(storage.getAll()[storage.size - 1], resume4);
    }

    @Test
    void removeResume() {
        storage.clear();
        Resume resume1 = new Resume(UUID_1);
        Resume resume2 = new Resume(UUID_2);
        Resume resume3 = new Resume(UUID_3);
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
        storage.removeResume(1);
        Assertions.assertEquals(resume3, storage.getAll()[1]);
        Assertions.assertNull(storage.getAll()[2]);
    }
}