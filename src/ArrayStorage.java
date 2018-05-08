/**
 * Array based storage for Resumes
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (size() == storage.length && storage.length != 0) {
            System.out.println("Storage is full");
        } else if (size() == 0)
            storage[0] = r;
        else
           storage[size()]=r;
    }

    Resume get(String uuid) {
        for (int i = 0; storage[i] != null; i++) {
            if (storage[i].uuid == uuid) {
                //System.out.println(storage[i]);
                return storage[i];
            }
        }
        return null;
    }
    //1 2 ...  39 40 : 40 -> 39, 39 = null

    void delete(String uuid) {
        for (int i = 0; storage[i] != null; i++) {
            if (storage[i].uuid == uuid) {
                for (int j = i; j < size() - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                storage[size() - 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] all = new Resume[size()];
        for (int i = 0; i < size(); i++) {
            all[i] = storage[i];
        }
        return all;
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null)
                break;
            else size++;
        }
        return size;

    }
}
