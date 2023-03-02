package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int indexOfResume = findIndexOfResume(resume);
        if (indexOfResume >= 0) {
            storage[indexOfResume] = resume;
        } else {
            System.out.println("Resume with " + resume.getUuid() + " is not present in storage");
        }
    }

    public void save(Resume r) {
        if (findIndexOfResume(r) < 0) {
            if (size < 10000) {
                storage[size] = r;
                size++;
            } else System.out.println("Excess the size of storage");
        } else System.out.println("Resume with " + r.getUuid() + " is already present in storage");
    }

    public Resume get(String uuid) {
        if (findResumeByUuid(uuid) != null) {
            return findResumeByUuid(uuid);
        } else {
            System.out.println("Resume with " + uuid + " is not present in storage");
            return null;
        }
    }

    public void delete(String uuid) {
        if (findResumeByUuid(uuid) != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        } else System.out.println("Resume with " + uuid + " is not present in storage");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        System.arraycopy(storage, 0, allResume, 0, size);
        return allResume;
    }

    public int size() {
        return size;
    }

    int findIndexOfResume(Resume resume) {
        Arrays.sort(storage, 0, size);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    Resume findResumeByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }
}
