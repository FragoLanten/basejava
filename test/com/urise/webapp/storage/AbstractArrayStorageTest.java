package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class AbstractArrayStorageTest {

    private final Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
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
        String uuid4 = "uuid4";
        try {
            storage.delete(uuid4);
            Assertions.fail("NotExistStorageException not thrown");
        } catch (NotExistStorageException e) {
            Assertions.assertNotNull(e);
        }
        //затем проверим, что метод корректно выполняется с существующим uuid
        Resume resume5 = new Resume("uuid5");
        storage.save(resume5);
        storage.delete("uuid2");
        Assertions.assertEquals(resume5, storage.getAll()[1]);
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void getAll() {
        storage.clear();
        Resume resume1 = new Resume(UUID_1);
        Resume resume2 = new Resume(UUID_2);
        Resume resume3 = new Resume(UUID_3);
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);

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