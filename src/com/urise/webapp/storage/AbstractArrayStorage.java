package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    final public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume with " + resume.getUuid() + " is not present in storage");
        }
        sort();
    }

    final public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Excess the size of storage");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume with " + resume.getUuid() + " is already present in storage");
        } else {
            insert(resume);
        }
    }

    final public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume with " + uuid + " is not present in storage");
            return null;
        } else {
            return storage[index];
        }
    }

    final public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            System.out.println("Resume with " + uuid + " is not present in storage");
        } else {
            updateIndexes(index);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void sort();

    protected abstract void insert(Resume resume);

    protected abstract void updateIndexes(int index);
}
