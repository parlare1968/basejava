package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = createResume("1111", "Григорий Кислин");
        // output fullName
        System.out.println(resume.getFullName());
        System.out.println("----------------------------");
        // output contacts
        for (ContactType type : ContactType.values()) {
            String contact = resume.getContact(type);
            if (contact != null) {
                System.out.println(type.getTitle() + ": " + contact);
            }
        }
        System.out.println("----------------------------");
        // output sections
        for (SectionType type : SectionType.values()) {
            AbstractSection section = resume.getSection(type);
            if (section != null) {
                System.out.println(type.getTitle());
                System.out.println(section);
            }
        }
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume;
        if (uuid == null) {
            resume = new Resume(fullName);
        } else {
            resume = new Resume(uuid, fullName);
        }
        // add contacts
        resume.addContact(ContactType.CELLPHONE, "+7(921) 111-1111");
        resume.addContact(ContactType.SKYPE, "skype:fullName");
        resume.addContact(ContactType.MAIL, "fullName@mail.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/fullName");
        resume.addContact(ContactType.GITHUB, "https://github.com/fullName");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/fullName");
        /*// add sections
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Objective"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Personal"));
        resume.addSection(SectionType.ACHIEVEMENTS, new ListSection("Achievement1", "Achievement2"));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("Qualification1", "Qualification2"));
        resume.addSection(SectionType.EXPERIENCE, new CompanySection(
                new Company("Company1", "Website1",
                        new Company.Period("Position11", 2015, 2, 2017, 4, null),
                        new Company.Period("Position12", 2017, 4, "Description12")),
                new Company("Company2", null,
                        new Company.Period("Position2", 2010, 9, 2015, 2, "Description2"))
        ));
        resume.addSection(SectionType.EDUCATION, new CompanySection(
                new Company("Course1", "Website1",
                        new Company.Period("Title11", 2013, 3, 2013, 5, "Description11"),
                        new Company.Period("Title12", 2014, 2, 2014, 4, "Description12")),
                new Company("Course2", "Website2",
                        new Company.Period("Title2", 2011, 3, 2011, 4, "Description2"))
        ));*/
        return resume;
    }
}
