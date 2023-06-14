package com.urise.webapp.model;
import java.util.List;
import java.util.Objects;

public class Organization {

    private final Link homePage;
    private final List<Period> periods;

    public Organization(String name, String url, List<Period> period) {
        this.homePage = new Link(name, url);
        this.periods = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", period=" + periods +
                '}';
    }
}
