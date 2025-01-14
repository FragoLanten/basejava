package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private List<String> items;

    public List<String> getItems() {
        return items;
    }

    public ListSection(List<String> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "listDescription=" + items +
                '}';
    }
}
