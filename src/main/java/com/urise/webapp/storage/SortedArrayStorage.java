package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    public Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid,"");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void insertResume(Resume resume, int index) {
        index = -index - 1;
        if (size - index >= 0) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
    }

    @Override
    protected void removeResume(int index) {
        if (size - index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
    }

}
