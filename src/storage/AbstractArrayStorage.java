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

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume not exists ");
        } else {
            storage[index] = r;
            System.out.println("Resume updated | uuid: " + index + " \n");
        }
    }

    public void save(Resume r) {

        if (size == 0) {
            storage[size++] = r;
            System.out.println("Resume added | uuid: " + r.getUuid());
            System.out.println("Insertion place: " + (size - 1));
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is full! ");
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Resume with that uuid already exists! ");
        } else {
            this.save0(r);
            System.out.println("Resume added | uuid: " + r.getUuid());
        }
    }

    public void delete(String uuid) {
        if (size == 0) {
            System.out.println("Storage empty ");
        } else if (getIndex(uuid) < 0) {
            System.out.println("Resume not exists");
        } else {
            this.delete0(uuid);
            System.out.println("Resume deleted ");
        }
    }

    public Resume get(String uuid) {
        Integer index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    public abstract void delete0(String uuid);

    public abstract void save0(Resume r);
}
