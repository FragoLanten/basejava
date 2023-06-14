package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] fileList = directory.listFiles();
        if (fileList == null) {
            throw new StorageException("no files in directory", "null");
        }
        for (File file : fileList) {
            doDelete(file.getName(), file);
        }
    }

    @Override
    public int size() {
        File[] fileList = directory.listFiles();
        if (fileList == null) {
            throw new StorageException("no files in directory", "null");
        }
        return fileList.length;
    }

    @Override
    public File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    public void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    public Resume doGet(String uuid, File file) {
        try {
            return doRead(uuid, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(String uuid, File file) {
        boolean canDelete;
        canDelete = file.delete();
        if (!canDelete) {
            throw new StorageException("File cannot be deleted", file.getName());
        }
    }

    @Override
    public List<Resume> doCopyAll() {
        ArrayList<Resume> resumeList = new ArrayList<>();
        File[] fileList = directory.listFiles();
        if (fileList == null) {
            throw new StorageException("no files in directory", "null");
        }
        for (File file : fileList) {
            resumeList.add(doGet(file.getName(), file));
        }
        return resumeList;
    }

    @Override
    public boolean isExist(File file) {
        return file.exists();
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(String string, File file) throws IOException;

}
