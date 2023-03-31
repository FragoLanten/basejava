package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected Object searchKey;

    @Override
    public void update(Resume resume) {
        if (getExistingSearchKey(resume.getUuid()) != null) {
            doUpdate(resume, searchKey);
        }
    }

    @Override
    public void save(Resume resume) {
        if (getNotExistingSearchKey(resume.getUuid()) != null) {
            doSave(resume, searchKey);
        }
    }

    @Override
    public Resume get(String uuid) {
        if (getExistingSearchKey(uuid) != null) {
            return doGet(uuid, searchKey);
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        if (getExistingSearchKey(uuid) != null) {
            doDelete(uuid, searchKey);
        }
    }

    public Object getExistingSearchKey(String uuid) {
        searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Object getNotExistingSearchKey(String uuid) {
        searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    public abstract void doUpdate(Resume resume, Object searchKey);

    public abstract void doSave(Resume resume, Object searchKey);

    public abstract Resume doGet(String uuid, Object searchKey);

    public abstract void doDelete(String uuid, Object searchKey);

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    public abstract Object getSearchKey(String uuid);

    public abstract boolean isExist(Object searchKey);

}
