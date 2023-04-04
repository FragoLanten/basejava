package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected int size;
    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Resume resume, Object searchKey) {
        int index = (int) searchKey;
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        int index = (int) searchKey;
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
    public Resume doGet(String uuid, Object searchKey) {
        int index = (int) searchKey;
        if (index <= -1) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
        }
    }

    @Override
    public void doDelete(String uuid, Object searchKey) {
        int index = (int) searchKey;
        if (index <= -1) {
            throw new NotExistStorageException(uuid);
        } else {
            removeResume(index);
            size--;
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(Arrays.asList(storage).subList(0, size));
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void removeResume(int index);

    public abstract Integer getSearchKey(String uuid);

    public boolean isExist(Object searchKey) {
        int index = (int) searchKey;
        return index >= 0 && index <= size;
    }
}
