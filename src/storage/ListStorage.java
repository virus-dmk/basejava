package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> resumesArrayList = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < resumesArrayList.size(); i++) {
            if (resumesArrayList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public int size() {
        return resumesArrayList.size();
    }

    @Override
    public void clear() {
        resumesArrayList.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumesArrayList.toArray(new Resume[0]);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumesArrayList.set((Integer) searchKey, resume);
    }

    @Override
    public void doDelete(Object searchKey) {
        resumesArrayList.remove(((Integer) searchKey).intValue());
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        resumesArrayList.add(resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumesArrayList.get((Integer) searchKey);
    }
}



