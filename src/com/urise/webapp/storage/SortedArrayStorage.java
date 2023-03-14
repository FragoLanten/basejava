package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume resume) {
        int left, right, mid;
        left = 0;
        right = size;
        //Ищем куда вставлять элемент, сравниваем с серединой отрезка,
        //если элемент меньше, то двигаем правый край отрезка, если больше - то левый край.
        while (left < right) {
            mid = (left + right) / 2;
            int result = storage[mid].compareTo(resume);
            if (result > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        size++;
        //Сдвигаем все элементы, больше вставляемого, на один вправо
        //Move biggers elements one by one to the right
        for (int i = size; i > left; i--) {
            storage[i] = storage[i - 1];
        }
        storage[left] = resume;
    }

    @Override
    protected void removeResume(int index) {
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
        size--;
    }
}
