import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void update(Resume resume) {
        int indexOfResume = findIndexOfResume(resume);
        if (indexOfResume >= 0) {
            storage[indexOfResume] = resume;
        } else {
            System.out.println("Resume is not present in storage");
        }
    }

    void save(Resume r) {
        if (findIndexOfResume(r)<0&&size<10000) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        return findResumeByUuid(uuid);
    }

    void delete(String uuid) {
        if (findResumeByUuid(uuid)!=null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        }
        else System.out.println("Resume with " + uuid + " is not present in storage");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        System.arraycopy(storage, 0, allResume, 0, size);
        return allResume;
    }

    int size() {
        return size;
    }

    int findIndexOfResume(Resume resume) {
        Arrays.sort(storage, 0, size);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    Resume findResumeByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }
}
