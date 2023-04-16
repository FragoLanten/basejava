package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ResumeMapStorage extends AbstractStorage<Resume> {
    final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void doSave(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(String uuid, Resume searchKey) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().equals(searchKey) && entry.getKey().equals(uuid)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void doDelete(String uuid, Resume searchKey) {
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
    public Resume getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isExist(Resume searchKey) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().equals(searchKey)) {
                return true;
            }
        }
        return false;
    }
}
