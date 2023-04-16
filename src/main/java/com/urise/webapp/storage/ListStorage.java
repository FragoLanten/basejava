package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    @Override
    public void doSave(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    @Override
    public Resume doGet(String uuid, Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    public void doDelete(String uuid, Integer searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    public List<Resume> doCopyAll() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean isExist(Integer searchKey) {
        if (searchKey != null) {
            return searchKey < storage.size();
        } else {
            return false;
        }
    }

}
