package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractArrayStorageTest {

    private final Storage storage = new ArrayStorage();

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
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void update() {
        //сначала проверяем чтобы выбросилось NotExistStorageException
        Resume newResume = new Resume("uuid4");
        try {
            storage.update(newResume);
            Assertions.fail("NotExistStorageException not thrown");
        } catch (NotExistStorageException e) {
            Assertions.assertNotNull(e);
        }
        //затем пробуем обновить резюме в массиве
        Resume resume3 = new Resume(UUID_3);
        storage.update(resume3);
        Assertions.assertEquals(storage.get(UUID_3),resume3);
    }

    @Test
    void save() {
        //сначала проверяем метод на выбрасывание StorageException
        storage.clear();
        for (int i = 0; i < 10000; i++) {
            Resume resume = new Resume();
            storage.save(resume);
        }
        try {
            storage.save(new Resume());
            Assertions.fail("StorageException not thrown");
        } catch (StorageException e) {
            Assertions.assertNotNull(e);
        }
        //затем проверяем метод на выбрасывание ExistStorageException
        storage.clear();
        storage.save(new Resume(UUID_1));
        try {
            storage.save(new Resume((UUID_1)));
            Assertions.fail("ExistStorageException not thrown");
        } catch (ExistStorageException e) {
            Assertions.assertNotNull(e);
        }
        //последним проверяем что резюме корректно сохранилось в массиве
        Resume resume2 = new Resume(UUID_2);
        storage.save(resume2);
        Assertions.assertEquals(storage.get(UUID_2), resume2);
    }

    @Test
    void get() {

    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    public void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }
}