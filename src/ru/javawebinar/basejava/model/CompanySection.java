package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class CompanySection extends Section {
    List<Company> companies;

    public CompanySection(Company... companies) {
        this.companies = Arrays.asList(companies);
    }

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return companies.equals(that.companies);
    }

    @Override
    public int hashCode() {
        return companies.hashCode();
    }

    @Override
    public String toString() {
        return companies.toString();
    }
}
