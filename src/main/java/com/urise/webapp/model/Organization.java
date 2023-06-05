package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class Organization {

    private final Link homePage;

    private final HashMap<LocalDate, LocalDate> period;

//    private final LocalDate startDate;
//    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Organization(String name, String url, HashMap<LocalDate, LocalDate> period, String title, String description) {

        Objects.requireNonNull(period, "period must not be null");
//        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.homePage = new Link(name, url);
        this.period = period;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!period.equals(that.period)) return false;
        if (!title.equals(that.title)) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + period.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", period=" + period +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
