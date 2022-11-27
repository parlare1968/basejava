package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;

    String website;

    List<Period> periods;

    public Company(String name, String website, Period... periods) {
        this.name = name;
        this.website = website;
        this.periods = Arrays.asList(periods);
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!name.equals(company.name)) return false;
        if (!website.equals(company.website)) return false;
        return periods.equals(company.periods);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + website.hashCode();
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", periods=" + periods +
                '}';
    }

    public static class Period implements Serializable {
        String title;

        LocalDate startDate;

        LocalDate endDate;

        String description;

        public Period(String title, int startDateYear, int startDateMonth, String description) {
            this.title = title;
            this.startDate = DateUtil.of(startDateYear, startDateMonth);
            this.endDate = LocalDate.now();
            this.description = description;
        }

        public Period(String title, int startDateYear, int startDateMonth, int endDateYear, int endDateMonth, String description) {
            this.title = title;
            this.startDate = DateUtil.of(startDateYear, startDateMonth);
            this.endDate = DateUtil.of(endDateYear, endDateMonth);
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Period period = (Period) o;

            if (!title.equals(period.title)) return false;
            if (!startDate.equals(period.startDate)) return false;
            if (!endDate.equals(period.endDate)) return false;
            return description.equals(period.description);
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + description.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "{" +
                    "title='" + title + '\'' +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}