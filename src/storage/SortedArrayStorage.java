package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void save0(Resume r) {
        int insIndex = (-(getIndex(r.getUuid())) - 1);
        for (int i = size - 1; i >= insIndex; i--) {
            storage[i + 1] = storage[i];
        }
        storage[insIndex] = r;
        size++;
    }

    @Override
    public void delete0(String uuid) {
        int delIndex = getIndex(uuid);
        storage[delIndex] = null;
        for (int i = delIndex; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
