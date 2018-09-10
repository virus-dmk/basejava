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
    public void update(Resume resume) {
        Integer index = getIndex(resume.getUuid());
        if (index == null) {
            System.out.println("model.Resume not exists\n\n");
        } else {
            storage[index] = resume;
            System.out.println("model.Resume updated!\n\n");
        }
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != null) {
            System.out.println("model.Resume already exists \n\n");
        } else {
            if (size >= STORAGE_LIMIT) {
                System.out.println("Storage is full\n\n");
            } else {
                storage[size++] = resume;
                System.out.printf("model.Resume saved, uuid: %s \n", resume.getUuid());
            }
        }
    }

    public void delete(String uuid) {
        Integer index = getIndex(uuid);
        if (index != null) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("model.Resume not exist \n\n");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    /**
     * @param uuid unuque id
     * @return if exist, return index of object in model.Resume massive,
     * else return null
     */
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
