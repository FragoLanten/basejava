/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            } else break;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null) {
                if ((resume.uuid).equals(uuid)) {
                    return resume;
                }
            } else break;
        }
        return null;
    }

    void delete(String uuid) {
        int recalculateCellsFromHere = -1;
        for (int i = 0; i < storage.length; i++) {
            if ((storage[i].uuid).equals(uuid)) {
                storage[i] = null;
                recalculateCellsFromHere = i;
                break;
            }
        }
        if (recalculateCellsFromHere != -1) {
            for (int i = recalculateCellsFromHere; i < storage.length; i++) {
                if (i == storage.length - 1) {
                    return;
                } else if (storage[i + 1] == null) {
                    storage[i] = null;
                    return;
                } else {
                    storage[i] = storage[i + 1];
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int sizeOfNotNullStorage = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                sizeOfNotNullStorage++;
            }
        }
        Resume[] notNullStorage = new Resume[sizeOfNotNullStorage];
        System.arraycopy(storage, 0, notNullStorage, 0, sizeOfNotNullStorage);
        return notNullStorage;
    }

    int size() {
        return getAll().length;
    }
}
