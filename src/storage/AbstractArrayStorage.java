package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    //protected abstract Integer getSearchKey(String uuid);

    public abstract void deleteElement(int index);

    public abstract void elementSave(Resume resume, int index);

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    public void doSave(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow ", resume.getUuid());
        } else {
            elementSave(resume, -((Integer) index) - 1);
            size++;
        }
    }

    @Override
    public void doDelete(Object index) {
        deleteElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}
