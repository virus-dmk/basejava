package storage;

import model.Resume;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MapStorageTest extends AbstractStorageTest {
    private static Storage storage = new MapStorage();
    public MapStorageTest() {
        super(storage);
    }

    @Test
    public void getAll() {
        Resume[] resResumes = storage.getAll();
        Arrays.sort(resResumes);
        assertEquals(3, resResumes.length);
        assertArrayEquals(new Resume[]{new Resume("uuid1"), new Resume("uuid2"), new Resume("uuid3")},resResumes);
    }
}