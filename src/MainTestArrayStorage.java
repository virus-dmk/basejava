import model.Resume;
import storage.AbstractArrayStorage;
import storage.ArrayStorage;
import storage.SortedArrayStorage;

/**
 * Test for com.urise.webapp.storage.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    static final AbstractArrayStorage SARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        Resume r2 = new Resume();
        Resume r3 = new Resume();
        Resume r4 = new Resume();
        Resume r5 = new Resume();
        Resume r6 = new Resume();
        Resume r7 = new Resume();

        r1.setUuid("uuid1");
        r2.setUuid("uuid2");
        r3.setUuid("uuid3");
        r4.setUuid("uuid4");
        r5.setUuid("uuid5");
        r6.setUuid("uuid6");
        r7.setUuid("uuid7");


        SARRAY_STORAGE.save(r1);
        SARRAY_STORAGE.save(r2);
        SARRAY_STORAGE.save(r3);
        SARRAY_STORAGE.save(r4);
        SARRAY_STORAGE.save(r5);
        SARRAY_STORAGE.save(r6);
        printAll();

        SARRAY_STORAGE.getAll();

        SARRAY_STORAGE.update(r1);
        SARRAY_STORAGE.update(r7);
        SARRAY_STORAGE.delete("uuid5");
        SARRAY_STORAGE.delete("uuid1");

        printAll();

        SARRAY_STORAGE.clear();
        printAll();

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
        System.out.println("Size: " + SARRAY_STORAGE.size());
    }
}
