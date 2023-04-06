package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.FullNameMapStorage;
import com.urise.webapp.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.urise.webapp.storage.AbstractStorage.RESUME_COMPARATOR;

class FullNameMapStorageTest {

    protected static final String UUID_1 = "uuid1";
    protected static final String FULL_NAME_1 = "Vadim";
    protected static final String UUID_2 = "uuid2";
    protected static final String FULL_NAME_2 = "Andrew";
    protected static final String UUID_3 = "uuid3";
    protected static final String FULL_NAME_3 = "Igor";
    protected static final String UUID_4 = "uuid4";
    protected static final String FULL_NAME_4 = "Denis";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
        RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
        RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
        RESUME_4 = new Resume(UUID_4, FULL_NAME_4);
    }

    Storage storage = new FullNameMapStorage();

    @BeforeEach
    void setUp() {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void doUpdate() {
        storage.save(RESUME_4);
        RESUME_4.setFullname("Anastasya");
        storage.update(RESUME_4);
        Assertions.assertSame(storage.get(UUID_4), RESUME_4);
    }

    @Test
    void doSave() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void doGet() {
        assertGet(RESUME_1);
    }

    @Test
    void doDelete() {
        storage.delete(UUID_3);
        Assertions.assertEquals(2, storage.size());
        assertSize(2);
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void updateNotExist() {
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

    @Test
    public void getAllSorted() {
        Map<String, Resume> testMap = new HashMap<>();
        testMap.put(UUID_2, RESUME_2);
        testMap.put(UUID_3, RESUME_3);
        testMap.put(UUID_1, RESUME_1);
        Collection<Resume> collection = testMap.values();
        ArrayList<Resume> testList = new ArrayList<>(collection);
        testList.sort(RESUME_COMPARATOR);
        Assertions.assertIterableEquals(testList, storage.getAllSorted());
    }

    void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }
}