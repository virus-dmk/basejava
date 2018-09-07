/**
 * Array based storage for Resumes
 */

import java.util.Arrays;

public class ArrayStorage {
    private int size = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void update(Resume resume) {
        if (resumeExists(resume)) {

            System.out.printf("Resume updated!\n\n");
        }
    }

    void save(Resume r) {
        if (resumeExists(r)) {
            return;
        } else {
            if (size == storage.length) {
                System.out.println("Storage is full/n/n");
            } else {

                storage[size++] = r;
                System.out.printf("Saved Resume, uuid: %s \n/n", r.uuid);
            }
        }

    }

    Resume get(String uuid) {
        if (resumeExists(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (resumeExists(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        }
    }

    boolean resumeExists(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.out.printf("Resume exists! \n");
                return true;
            }
        }
        System.out.printf("Resume not exists! \n");
        return false;
    }

    boolean resumeExists(Resume r) {
        for (int i = 0; i < size; i++) {
            if (r.uuid.equals(storage[i].uuid)) {
                System.out.printf("Resume exists! \n");
                return true;
            }
        }
        System.out.printf("Resume not exists! \n");
        return false;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
