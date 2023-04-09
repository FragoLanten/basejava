package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    public Resume doGet(String uuid, Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    public void doDelete(String uuid, Object searchKey) {
        storage.remove(uuid);
    }

    @Override
    public List<Resume> getAllSorted(){
        Collection<Resume> collection = storage.values();
        ArrayList<Resume> finalList = new ArrayList<>(collection);
        finalList.sort(RESUME_COMPARATOR);
        return finalList;
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
    public boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

}
