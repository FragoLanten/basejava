package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        Objects.requireNonNull(fileList, "fileList must not be null");
        for (File file : fileList) {
            if (file == null) {
                throw new StorageException("file is null", "no Uuid");
            } else {
                doDelete(file.getName(), file);
            }
        }
    }

    @Override
    public int size() {
        int count = 0;
        File[] fileList = directory.listFiles();
        Objects.requireNonNull(fileList, "fileList must not be null");
        for (File file : fileList) {
            count++;
        }
        return count;
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

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(String string, File file) throws IOException;

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
        Objects.requireNonNull(fileList, "fileList must not be null");
        for (File file : fileList) {
            if (file == null) {
                throw new StorageException("file is null", "no Uuid");
            } else {
                resumeList.add(doGet(file.getName(), file));
            }
        }
        return resumeList;
    }

    @Override
    public boolean isExist(File file) {
        return file.exists();
    }

}
