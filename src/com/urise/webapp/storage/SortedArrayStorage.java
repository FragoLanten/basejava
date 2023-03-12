package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume with " + resume.getUuid() + " is not present in storage");
        }
        //Хотел сюда поставить метод Arrays.sort, но по тз запрещено использовать данный метод,
        // поэтому применял пузырковую сортировку
        for (int i = 0; i < size - 1; i++) {
            if (storage[i].compareTo(storage[i + 1]) > 0) {
                Resume tempResume = storage[i];
                storage[i] = storage[i + 1];
                storage[i + 1] = tempResume;
            }
        }
    }

    @Override
    public void save(Resume resume) {
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
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            System.out.println("Resume with " + uuid + " is not present in storage");
        } else {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
