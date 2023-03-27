package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume resume) {
        if (storage.containsKey(resume.getUuid())) {
            storage.put(resume.getUuid(), resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        if (storage.containsValue(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            storage.put(resume.getUuid(), resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return entry.getValue();
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (storage.containsKey(uuid)) {
            storage.remove(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void doUpdate(Resume resume) {

    }

    @Override
    public void doSave(Resume resume) {

    }

    @Override
    public Resume doGet(String uuid) {
        return null;
    }

    @Override
    public void doDelete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    public boolean isExist(Object searchKey) {
        return false;
    }

}
