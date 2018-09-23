package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

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

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume not exists ");
        } else {
            storage[index] = resume;
            System.out.println("Resume updated | uuid: " + index + " \n");
        }
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is full! ");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume with that uuid already exists! ");
        } else {
            int index = (-(getIndex(resume.getUuid())) - 1);
            doSave(resume,index);
            size++;
            System.out.println("Resume added | uuid: " + resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume not exists");
        } else {
            doDelete(index);
            size--;
            System.out.println("Resume deleted ");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    public abstract void doDelete(int index);

    public abstract void doSave(Resume resume, int index);
}
