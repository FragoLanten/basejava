package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {

    final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    @Override
    public void doSave(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    @Override
    public Resume doGet(String uuid, String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    public void doDelete(String uuid, String searchKey) {
        storage.remove(uuid);
    }

    @Override
    public List<Resume> doCopyAll() {
        Collection<Resume> collection = storage.values();
        return new ArrayList<>(collection);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public boolean isExist(String searchKey) {
        return storage.containsKey((String) searchKey);
    }

}
