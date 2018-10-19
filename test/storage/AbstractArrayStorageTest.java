package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private final Resume R1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private final Resume R2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private final Resume R3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        assertEquals(3, storage.getAll().length);
        Resume[] r = storage.getAll();
        assertEquals(r[0].getUuid(), "uuid1");
        assertEquals(r[1].getUuid(), "uuid2");
        assertEquals(r[2].getUuid(), "uuid3");
    }

    @Test
    public void update() {
        Resume r = storage.get("uuid1");
        storage.update(r);
        assertSame(r, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume r = new Resume("notExist");
        storage.update(r);
    }

    @Test
    public void save() {
        storage.save(new Resume("newUuid"));
        assertEquals(storage.size(), 4);
        assertNotNull(storage.get("newUuid"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume("uuid1"));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
        storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("notExist");
    }

    @Test
    public void get() {
        assertEquals(R1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("notExist");
    }
}