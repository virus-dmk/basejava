package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private final Resume RESUME1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private final Resume RESUME2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private final Resume RESUME3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private final Resume RESUME4 = new Resume(UUID_4);

    public AbstractStorageTest(Storage storage) {
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
        Resume resume = new Resume("uuid1");
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume resume = new Resume("notExist");
        storage.update(resume);
    }

    @Test
    public void save() {
        storage.save(RESUME4);
        assertEquals(4, storage.size());
        assertNotNull(storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME1);
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