package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection{
    private List<Period> periods;

    private String name;

    private String website;

    public List<Period> getPeriods() {
        return periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return this.periods.equals(that.periods) && this.name.equals(that.name) && this.website.equals(that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, name, website);
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "periods=" + periods +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
