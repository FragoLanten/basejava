package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public abstract void clear();

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
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertResume(resume, index);
            size++;
        }
    }

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
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            throw new NotExistStorageException(uuid);
        } else {
            removeResume(index);
            size--;
        }
    }

    public abstract void doUpdate();

    public abstract void doSave();

    public abstract void doGet();

    public abstract void doDelete();

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    public abstract Object getSearchKey(String uuid);

    public abstract boolean isExist(Object searchKey);

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void removeResume(int index);

    public Object getExistingSearchKey(String uuid) {
        if (isExist(getSearchKey(uuid))) {
            return getSearchKey(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Object getNotExistingSearchKey(String uuid) {
        if (isExist(getSearchKey(uuid))) {
            throw new ExistStorageException(uuid);
        } else {
            return getSearchKey(uuid);
        }
    }

}
