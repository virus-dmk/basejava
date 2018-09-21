package storage;
/**
 * Array based storage for Resumes
 */

import model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * <p>updates object in model.Resume array with the same resume uuid</p>
     *
     * @param resume
     */

    public void save0(Resume resume) {
        storage[size++] = resume;
    }

    public void delete0(String uuid) {
        storage[getIndex(uuid)] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @param uuid unuque id
     * @return if exist, return index of object in model.Resume massive,
     * else return null
     */
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
