package storage;

import model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    public void elementSave(Resume resume, int index) {
        storage[size] = resume;
    }

    public void deleteElement(int index) {
        storage[index] = storage[size - 1];
    }

    /**
     * @param uuid unuque id
     * @return if exist, return index of object in model.Resume massive,
     * else return null
     */
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
