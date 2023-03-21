package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest {

    protected final Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected static final String UUID_1 = "uuid1";
    static Resume resume1 = new Resume(UUID_1);
    protected static final String UUID_2 = "uuid2";
    static Resume resume2 = new Resume(UUID_2);
    protected static final String UUID_3 = "uuid3";
    static Resume resume3 = new Resume(UUID_3);


    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() {
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
        Assertions.assertEquals(storage.getAll()[2], resume3);
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
        Assertions.assertEquals(storage.getAll()[1], resume2);
    }

    @Test
    void get() {
        //сначала проверим метод get на то, чтобы выбрасывалось Exception на несуществующий uuid
        String uuid4 = "uuid4";
        try {
            storage.get(uuid4);
            Assertions.fail("NotExistStorageException not thrown");
        } catch (NotExistStorageException e) {
            Assertions.assertNotNull(e);
        }
        //затем проверим, что метод корректно выполняется с существующим uuid
        Resume resume5 = new Resume("uuid5");
        storage.save(resume5);
        Assertions.assertEquals(resume5, storage.get("uuid5"));
    }

    @Test
    void delete() {
        //сначала проверим метод delete на то, чтобы выбрасывалось Exception на несуществующий uuid
        String uuid6 = "uuid6";
        try {
            storage.delete(uuid6);
            Assertions.fail("NotExistStorageException not thrown");
        } catch (NotExistStorageException e) {
            Assertions.assertNotNull(e);
        }
        //затем проверим, что метод корректно выполняется с существующим uuid
        storage.delete("uuid3");
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    void getAll() {
        final Resume[] testStorage = new Resume[3];
        testStorage[0] = resume1;
        testStorage[1] = resume2;
        testStorage[2] = resume3;
        Assertions.assertArrayEquals(testStorage, storage.getAll());
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