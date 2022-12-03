package ru.javawebinar.basejava.storage.serializer;

import java.io.IOException;

@FunctionalInterface
public interface ResumeDataReader {
    void read() throws IOException;
}
