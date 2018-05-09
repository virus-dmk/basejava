/**
 * Array based storage for Resumes
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class ArrayStorage {
    private int size = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size--; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage is full");
        } else {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i<size; i++) {
            if (storage[i].uuid == uuid) {
                //System.out.println(storage[i]);
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i<size; i++) {
            if (storage[i].uuid == uuid) {

                for (int j = i; j < size-1; j++) {
                    storage[j] = storage[j + 1];
                }
                storage[size - 1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] all = new Resume[size()];
        for (int i = 0; i < size; i++) {
            all[i] = storage[i];
        }
        return all;
    }

    int size() {
        return size;
    }
}
