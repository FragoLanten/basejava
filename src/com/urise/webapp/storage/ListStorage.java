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
        storage.set((int) searchKey, resume);
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    public Resume doGet(String uuid, Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    public void doDelete(String uuid, Object searchKey) {
        storage.remove((int) searchKey);
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
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean isExist(Object searchKey) {
        if (searchKey != null) {
            return (int) searchKey < storage.size();
        } else {
            return false;
        }
    }

}
