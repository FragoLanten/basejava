package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    protected static final String UUID_4 = "uuid4";
    static Resume resume4 = new Resume(UUID_4);


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
        assertSize(0);
        Assertions.assertIterableEquals(Arrays.asList(new Resume[]{}), Arrays.asList(storage.getAll()));
    }

    @Test
    void update() {
        //сначала проверяем чтобы выбросилось NotExistStorageException
        Resume newResume = new Resume("uuid4");
        updateNotExist(newResume);
        //затем пробуем обновить резюме в массиве
        Resume resume3 = new Resume(UUID_3);
        storage.update(resume3);
        Assertions.assertSame(storage.getAll()[2], resume3);
    }

    @Test
    void save() {
        //сначала проверяем метод на выбрасывание StorageException
        storageOverflow();
        //затем проверяем метод на выбрасывание ExistStorageException
        storage.clear();
        saveExist();
        //последним проверяем что резюме корректно сохранилось в массиве
        storage.save(resume4);
        asserGet(resume4);
        assertSize(2);
    }

    @Test
    void get() {
        //сначала проверим метод get на то, чтобы выбрасывалось Exception на несуществующий uuid
        String uuid4 = "uuid4";
        getNotExist(uuid4);
        //затем проверим, что метод корректно выполняется с существующим uuid
        asserGet(resume1);
        asserGet(resume2);
        asserGet(resume3);
    }

    @Test
    void delete() {
        //сначала проверим метод delete на то, чтобы выбрасывалось Exception на несуществующий uuid
        String uuid6 = "uuid6";
        deleteNotExist(uuid6);
        //затем проверим, что метод корректно выполняется с существующим uuid
        storage.delete("uuid3");
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    void getAll() {
        final Resume[] testStorage = new Resume[]{resume1, resume2, resume3};
        Assertions.assertArrayEquals(testStorage, storage.getAll());
        assertSize(3);
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    public void getNotExist(String uuid) {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(uuid));
    }

    public void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    public void asserGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    public void saveExist() {
        storage.save(new Resume(UUID_1));
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    public void storageOverflow() {
        storage.clear();
        for (int i = 0; i < 10000; i++) {
            Resume resume = new Resume();
            storage.save(resume);
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(resume4));
    }

    public void deleteNotExist(String uuid) {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(uuid));
    }

    public void updateNotExist(Resume resume) {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(resume));
    }
}