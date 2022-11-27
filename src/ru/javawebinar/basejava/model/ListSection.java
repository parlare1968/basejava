package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<String> list;

    public ListSection(List<String> list) {
        this.list = list;
    }

    public ListSection(String... list) {
        this.list = Arrays.asList(list);
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}