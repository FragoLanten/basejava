package com.urise.webapp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private String uuid;

    private String fullname;

    public Resume(String fullname) {
        this.uuid = UUID.randomUUID().toString();
        this.fullname = fullname;
    }

    public Resume(String uuid, String fullname) {
        this.uuid = uuid;
        this.fullname = fullname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return this.uuid.equals(resume.uuid) && this.fullname.equals(resume.fullname);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullname.hashCode();
        return result;
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullname.compareTo(o.fullname);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
