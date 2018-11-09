package storage;

import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractStorageTest {
    private static Storage storage = new ArrayStorage();
    public ArrayStorageTest() {
        super(storage);
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
}