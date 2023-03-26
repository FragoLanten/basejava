package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorage implements Storage{

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }
    @Override
    public abstract void save(Resume resume);

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
        }
    }
    @Override
    public abstract void delete(String uuid);
    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    protected abstract int getIndex(String uuid);

}
