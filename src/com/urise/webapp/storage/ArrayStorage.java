package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    final int STORAGE_LIMIT = 10000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume);
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume with " + resume.getUuid() + " is not present in storage");
        }
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Excess the size of storage");
        } else if (getIndex(r) >= 0) {
            System.out.println("Resume with " + r.getUuid() + " is already present in storage");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        int index = getIndex(resume);
        if (index == -1) {
            System.out.println("Resume with " + uuid + " is not present in storage");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        Resume resume = get(uuid);
        int index = getIndex(resume);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    int getIndex(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(resume)) {
                return i;
            }
        }
        return -1;
    }
}
