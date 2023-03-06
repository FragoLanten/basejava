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
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Resume with " + uuid + " is not present in storage");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = -1;
        Resume resume = get(uuid);
        if (resume != null) {
            index = getIndex(resume);
        }
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
        Arrays.sort(storage, 0, size);
        if (Arrays.binarySearch(storage, 0, size, resume) >= 0) {
            return Arrays.binarySearch(storage, 0, size, resume);
        } else return -1;
    }
}
