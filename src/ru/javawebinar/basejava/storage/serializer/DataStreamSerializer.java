package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements Serializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            writeText(dos, r.getUuid());
            writeText(dos, r.getFullName());
            // записываем контакты
            writeCollection(dos, r.getContacts().entrySet(), entry -> {
                writeText(dos, entry.getKey().name());
                writeText(dos, entry.getValue());
            });
            // записываем секции
            writeCollection(dos, r.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                AbstractSection section = entry.getValue();
                writeText(dos, sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        writeText(dos, ((TextSection) section).getText());
                        break;
                    case ACHIEVEMENTS:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) section).getList(), text -> writeText(dos, text));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((CompanySection) section).getCompanies(), company -> {
                            writeText(dos, company.getName());
                            writeText(dos, company.getWebsite());
                            writeCollection(dos, company.getPeriods(), period -> {
                                writeText(dos, period.getTitle());
                                writeLocalDate(dos, period.getStartDate());
                                writeLocalDate(dos, period.getEndDate());
                                writeText(dos, period.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = readText(dis);
            String fullName = readText(dis);
            Resume resume = new Resume(uuid, fullName);
            readCollection(dis, () -> resume.addContact(ContactType.valueOf(readText(dis)), readText(dis)));
            readCollection(dis, () -> {
                SectionType sectionType = SectionType.valueOf(readText(dis));
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        addResumeSection(resume, sectionType, new TextSection(readText(dis)));
                        break;
                    case ACHIEVEMENTS:
                    case QUALIFICATIONS:
                        addResumeSection(resume, sectionType, new ListSection(readList(dis)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        addResumeSection(resume, sectionType, new CompanySection(readCompany(dis)));
                        break;
                }
            });
            return resume;
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ResumeDataWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void writeText(DataOutputStream dos, String text) throws IOException {
        dos.writeUTF(text);
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeUTF(ld.toString());
    }

    private void readCollection(DataInputStream dis, ResumeDataReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private String readText(DataInputStream dis) throws IOException {
        return dis.readUTF();
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.parse(dis.readUTF());
    }

    private List<String> readList(DataInputStream dis) throws IOException {
        List<String> list = new ArrayList<>();
        readCollection(dis, () -> list.add(readText(dis)));
        return list;
    }

    private List<Company> readCompany(DataInputStream dis) throws IOException {
        List<Company> companies = new ArrayList<>();
        readCollection(dis, () -> {
            String name = readText(dis);
            String website = readText(dis);
            List<Company.Period> periods = new ArrayList<>();
            readCollection(dis, () -> periods.add(new Company.Period(readText(dis), readLocalDate(dis), readLocalDate(dis), readText(dis))));
            companies.add(new Company(name, website, periods));
        });
        return companies;
    }

    private void addResumeSection(Resume resume, SectionType sectionType, AbstractSection abstractSection) {
        resume.addSection(sectionType, abstractSection);
    }
}
