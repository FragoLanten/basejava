package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    protected static final int STORAGE_LIMIT = 10000;
    protected Storage storage;
    protected SK searchKey;

    protected static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing(Resume::getFullname)
                    .thenComparing(Resume::getUuid);

    public abstract void doUpdate(Resume resume, SK searchKey);

    public abstract void doSave(Resume resume, SK searchKey);

    public abstract Resume doGet(String uuid, SK searchKey);

    public abstract void doDelete(String uuid, SK searchKey);

    public abstract List<Resume> doCopyAll();

    @Override
    public abstract int size();

    public abstract SK getSearchKey(String uuid);

    public abstract boolean isExist(SK searchKey);

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        if (getExistingSearchKey(resume.getUuid()) != null) {
            doUpdate(resume, searchKey);
        }
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistingSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        if (getExistingSearchKey(uuid) != null) {
            return doGet(uuid, searchKey);
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        if (getExistingSearchKey(uuid) != null) {
            doDelete(uuid, searchKey);
        }
    }

    public SK getExistingSearchKey(String uuid) {
        searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public SK getNotExistingSearchKey(String uuid) {
        searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resultList = doCopyAll();
        resultList.sort(AbstractStorage.RESUME_COMPARATOR);
        return resultList;
    }

}
