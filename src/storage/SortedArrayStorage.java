package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume r) {
        Integer index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume not exists ");
        } else {
            storage[index] = r;
            System.out.println("Resume updated | uuid: " + index + " \n");
        }
    }

    @Override
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
            int insIndex = (-(Arrays.binarySearch(storage, 0, size, r)) - 1);
            for (int i = size - 1; i >= insIndex; i--) {
                storage[i + 1] = storage[i];
            }
            storage[insIndex] = r;
            System.out.println("Resume added | uuid: " + r.getUuid());
            System.out.println("Insertion place: " + insIndex);
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        if (size == 0) {
            System.out.println("Storage empty ");
        } else if (getIndex(uuid) < 0) {
            System.out.println("Resume not exists");
        } else {
            int delIndex = getIndex(uuid);
            storage[delIndex] = null;
            for (int i = delIndex; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            System.out.println("Resume deleted ");
            size--;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected Integer getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
