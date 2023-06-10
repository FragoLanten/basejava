package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static Resume createResume (String uuid, String fullname) {
        Resume testResume = new Resume(uuid, fullname);

        Map<ContactType, String> contacts = new HashMap<>();
        contacts.put(ContactType.TELEPHONE, "8-800-500-31-02");
        contacts.put(ContactType.EMAIL, "rizinvadim@gmail.com");
        testResume.setContacts(contacts);

        Map<SectionType, Section> sections = new HashMap<>();

        Section positionSection = new TextSection(
                "Java Стажер");
        sections.put(SectionType.OBJECTIVE, positionSection);

        Section personalSection = new TextSection(
                "Нахожусь в начале пути изучения Java, поглощаю массу новых знаний, прохожу стажировку");
        sections.put(SectionType.PERSONAL, personalSection);

        List<String> achievementList = new ArrayList<>();
        achievementList.add("Прошёл курс JavaRush");
        achievementList.add("Прошёл курс Stepik \"Java для Чайников\"");
        Section achievementSection = new ListSection(achievementList);
        sections.put(SectionType.ACHIEVEMENT, achievementSection);

        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("Java Core, Collections, Generics, Maven, Spring, MVC");
        qualificationList.add("SQL, MySQL, PostegreSQL");
        Section qualificationSection = new ListSection(qualificationList);
        sections.put(SectionType.QUALIFICATIONS, qualificationSection);

        ArrayList<Period> periodsOfStudy = new ArrayList<>();
        Period periodOfStudy1 = new Period(LocalDate.of(2023, 2, 10),LocalDate.now(),
                "Программиcт Стажер", "Создание базы резюме");
        periodsOfStudy.add(periodOfStudy1);
        Organization topJava = new Organization("Top Java", "top-java.ru", periodsOfStudy);
        List<Organization> listOfWorkPeriods = new ArrayList<>();
        listOfWorkPeriods.add(topJava);
        Section experienceSection = new OrganizationSection(listOfWorkPeriods);
        sections.put(SectionType.EXPERIENCE, experienceSection);

        ArrayList<Period> periodsOfWork = new ArrayList<>();
        Period periodOfWork1 = new Period(LocalDate.of(2007, 9, 1), LocalDate.of(2012, 6, 30),
                "Экономист Бухгалтер", "дневное обучение");
        periodsOfWork.add(periodOfWork1);
        Organization mati = new Organization("Мати РГТУ", "mati-rgtu.ru", periodsOfWork);
        List<Organization> listOfStudyPeriods = new ArrayList<>();
        listOfStudyPeriods.add(mati);
        Section studySection = new OrganizationSection(listOfStudyPeriods);
        sections.put(SectionType.EDUCATION, studySection);

        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        for (Map.Entry<SectionType, Section> section : sections.entrySet()) {
            System.out.println(section.getKey().getTitle() + " " + section.getValue());
        }
        return testResume;
    }

}
