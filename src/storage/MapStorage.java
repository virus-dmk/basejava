package storage;

import model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> hashMap = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        for (Map.Entry<String, Resume> entry : hashMap.entrySet()) {
            if (entry.getKey().equals(resume.getUuid())) {
                entry.setValue(resume);
            }
        }
    }

    @Override
    protected boolean isExist(Object searchKey) {
        Resume r = hashMap.get(searchKey);
        return r != null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public void doDelete(Object searchKey) {
        hashMap.remove(searchKey);
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        hashMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return hashMap.get(searchKey);
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public Resume[] getAll() {
        Collection<Resume> resumes = hashMap.values();
        return resumes.toArray(new Resume[hashMap.size()]);
    }

    @Override
    public int size() {
        return hashMap.size();
    }
}
