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
        Integer index = resumeExists(resume.uuid);
        if (index != null) {
            storage[index]=resume;
            System.out.printf("Resume updated!\n\n");
        } else {
            System.out.printf("Resume not exists\n\n");
        }
    }

    void save(Resume resume) {
        if (resumeExists(resume.uuid) != null) {
            System.out.printf("Resume already exists \n\n");
        } else {
            if (size == storage.length) {
                System.out.println("Storage is full\n\n");
            } else {
                storage[size++] = resume;
                System.out.printf("Resume saved, uuid: %s \n", resume.uuid);
            }
        }
    }

    Resume get(String uuid) {
        Integer index = resumeExists(uuid);
        if (index != null) {
            return storage[index];
        } else {
            System.out.printf("Resume not exists\n\n");
            return null;
        }
    }

    void delete(String uuid) {
        Integer index = resumeExists(uuid);
        if (index != null) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @param uuid unuque id
     * @return if exist, return index of object in Resume massive,
     * else return null
     */
    Integer resumeExists(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return null;
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
