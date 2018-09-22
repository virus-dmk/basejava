package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void doSave(Resume resume) {
        int insIndex = (-(getIndex(resume.getUuid())) - 1);
        System.arraycopy(storage, insIndex, storage, insIndex + 1, size - insIndex);
        storage[insIndex] = resume;
    }

    @Override
    public void doDelete(int delIndex) {
        storage[delIndex] = null;
        System.arraycopy(storage, delIndex + 1, storage, delIndex, size - 1 - delIndex);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
