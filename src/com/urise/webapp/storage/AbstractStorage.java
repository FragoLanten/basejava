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
        if (getExistingSearchKey(resume.getUuid()) != null) {
            doUpdate(resume);
        }
    }

    @Override
    public void save(Resume resume) {
        if (getNotExistingSearchKey(resume.getUuid()) != null) {
            doSave(resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        if (getExistingSearchKey(uuid) != null) {
            return doGet(uuid);
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        if (getExistingSearchKey(uuid) != null) {
            doDelete(uuid);
        }
    }

    public abstract void doUpdate(Resume resume);

    public abstract void doSave(Resume resume);

    public abstract Resume doGet(String uuid);

    public abstract void doDelete(String uuid);

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    public abstract Object getSearchKey(String uuid);

    public abstract boolean isExist(Object searchKey);

    public Object getExistingSearchKey(String uuid) {
        if (isExist(getSearchKey(uuid))) {
            return uuid;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Object getNotExistingSearchKey(String uuid) {
        if (isExist(getSearchKey(uuid))) {
            throw new ExistStorageException(uuid);
        } else {
            return uuid;
        }
    }

}
