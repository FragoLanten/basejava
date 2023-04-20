package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection{

    private List<String> listDescription;

    public List<String> getListDescription() {
        return listDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return listDescription.equals(that.listDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listDescription);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "listDescription=" + listDescription +
                '}';
    }
}
