package com.urise.webapp.model;

import java.util.Objects;

public class TextSection extends Section {

    private final String content;



    public TextSection(String description) {
        Objects.requireNonNull(description, "content must not be null");
        this.content = description;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "description='" + content + '\'' +
                '}';
    }
}
