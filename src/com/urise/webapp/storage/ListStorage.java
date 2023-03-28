package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume resume) {
        storage.set(storage.indexOf(resume), resume);
    }

    @Override
    public void doSave(Resume resume) {
        storage.add(resume);
    }

    @Override
    public Resume doGet(String uuid) {
        return (Resume) getSearchKey(uuid);
    }

    @Override
    public void doDelete(String uuid) {
        Resume resume = (Resume) getSearchKey(uuid);
        storage.remove(resume);
    }

    @Override
    public Resume[] getAll() {
        return null;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Object getSearchKey(String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    @Override
    public boolean isExist(Object searchKey) {
        return storage.contains((Resume) searchKey);
    }

}
