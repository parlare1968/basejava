package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

public class ResumeTestData {
    private static final Resume RESUME = new Resume("Григорий Кислин");

    public static void main(String[] args) {
        // add contacts
        addContact(ContactType.CELLPHONE, "+7(921) 855-0482");
        addContact(ContactType.SKYPE, "skype:grigory.kislin");
        addContact(ContactType.MAIL, "gkislin@yandex.ru");
        addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        addContact(ContactType.GITHUB, "https://github.com/gkislin");
        addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        // add sections
        addSection(SectionType.OBJECTIVE, "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        addSection(SectionType.PERSONAL, "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры");
        addSection(SectionType.ACHIEVEMENTS,
                "Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
        );
        addSection(SectionType.QUALIFICATIONS,
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\""
        );
        addSection(SectionType.EXPERIENCE,
                new Company("Java Online Projects", "https://javaops.ru/",
                        new Company.Period("Автор проекта.", 2016, 10, "Создание, организация и проведение Java онлайн проектов и стажировок.")),
                new Company("Wrike", "https://www.wrike.com/",
                        new Company.Period("Старший разработчик (backend)", 2014, 10, 2016, 1, "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))
        );
        addSection(SectionType.EDUCATION,
                new Company("Coursera", "https://www.coursera.org/learn/scala-functional-programming",
                        new Company.Period("'Functional Programming Principles in Scala' by Martin Odersky", 2013, 3, 2013, 5, "")),
                new Company("Luxoft", "https://ibs-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
                        new Company.Period("Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", 2011, 3, 2011, 4, ""))
        );

        // output fullName
        System.out.println(RESUME.getFullName());
        System.out.println("----------------------------");
        // output contacts
        for (ContactType type : ContactType.values()) {
            String contact = RESUME.getContact(type);
            if (contact != null) {
                System.out.println(type.getTitle() + ": " + contact);
            }
        }
        System.out.println("----------------------------");
        // output sections
        for (SectionType type : SectionType.values()) {
            AbstractSection section = RESUME.getSection(type);
            if (section != null) {
                System.out.println(type.getTitle());
                System.out.println(section);
            }
        }
    }

    static void addContact(ContactType type, String text) {
        RESUME.addContact(type, text);
    }

    static void addSection(SectionType type, String text) {
        RESUME.addSection(type, new TextSection(text));
    }

    static void addSection(SectionType type, String... list) {
        RESUME.addSection(type, new ListSection(list));
    }

    static void addSection(SectionType type, Company... list) {
        RESUME.addSection(type, new CompanySection(list));
    }
}
