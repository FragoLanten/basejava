package com.urise.webapp.storage;

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
    public void doUpdate(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void doSave(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(String uuid) {
        return (Resume) getSearchKey(uuid);
    }

    @Override
    public void doDelete(String uuid) {
        storage.remove(uuid);
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
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isExist(Object searchKey) {
        return storage.containsValue((Resume) searchKey);
    }

}
