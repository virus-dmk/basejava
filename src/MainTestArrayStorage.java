import model.Resume;
import storage.AbstractArrayStorage;
import storage.SortedArrayStorage;

/**
 * Test for com.urise.webapp.storage.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    static final AbstractArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");
        Resume r5 = new Resume("uuid5");
        Resume r6 = new Resume("uuid6");
        Resume r7 = new Resume("uuid7");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r6);
        printAll();

        ARRAY_STORAGE.getAll();

        ARRAY_STORAGE.update(r1);
        ARRAY_STORAGE.update(r7);
        ARRAY_STORAGE.delete("uuid5");
        ARRAY_STORAGE.delete("uuid1");

        printAll();

        ARRAY_STORAGE.clear();
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
        for (Resume resume : ARRAY_STORAGE.getAll()) {
            System.out.println(resume);
        }
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }
}
