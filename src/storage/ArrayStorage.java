package storage;
/**
 * Array based storage for Resumes
 */

import model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    /**
     * <p>updates object in model.Resume array with the same resume uuid</p>
     *
     * @param resume
     */

    public void doSave(Resume resume) {
        storage[size] = resume;
    }

    public void doDelete(int delIndex) {
        storage[delIndex] = storage[size - 1];
        storage[size - 1] = null;
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
