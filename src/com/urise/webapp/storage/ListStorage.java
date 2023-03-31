package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume resume, Object searchKey) {
        storage.set(storage.indexOf(resume), resume);
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    public Resume doGet(String uuid, Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    public void doDelete(String uuid, Object searchKey) {
        storage.remove((Resume) searchKey);
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
