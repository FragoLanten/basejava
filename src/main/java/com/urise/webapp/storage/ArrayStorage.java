package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void removeResume(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

}
