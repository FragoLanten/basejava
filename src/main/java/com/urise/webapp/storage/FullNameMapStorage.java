package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class FullNameMapStorage extends AbstractStorage {
    final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(String uuid, Object searchKey) {
        for (Map.Entry<String,Resume> entry : storage.entrySet()) {
            if (entry.getValue().getFullname().equals(searchKey)&&(entry.getKey().equals(uuid))) {
                return entry.getValue();
            }
        }
        return null;
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
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return entry.getValue().getFullname();
            }
        }
        return null;
    }

    @Override
    public boolean isExist(Object searchKey) {
        for (Map.Entry<String,Resume> entry : storage.entrySet()) {
            if (entry.getValue().getFullname().equals(searchKey)) {
                return true;
            }
        }
        return false;
    }
}
