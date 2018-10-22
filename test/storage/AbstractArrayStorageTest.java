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
    private final Resume RESUME1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private final Resume RESUME2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private final Resume RESUME3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
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
        Resume[] resResumes = storage.getAll();
        assertEquals(3, resResumes.length);
        assertArrayEquals(resResumes, new Resume[]{RESUME1, RESUME2, RESUME3});
    }

    @Test
    public void update() {
        Resume r = storage.get(UUID_1);
        storage.update(r);
        assertSame(r, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume resume = new Resume("notExist");
        storage.update(resume);
    }

    @Test
    public void save() {
        storage.save(new Resume("newUuid"));
        assertEquals(4, storage.size());
        assertNotNull(storage.get("newUuid"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Array overflow erlier than was planned");
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("notExist");
    }

    @Test
    public void get() {
        assertEquals(RESUME1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("notExist");
    }
}