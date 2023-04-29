package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {

    private final LocalDate start;

    private final LocalDate end;

    private final String title;

    private final String description;

    public Period(LocalDate start, LocalDate end, String title, String description) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return start.equals(period.start) && end.equals(period.end) && title.equals(period.title) && description.equals(period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, title, description);
    }

    @Override
    public String toString() {
        return "Period{" +
                "start=" + start +
                ", end=" + end +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
