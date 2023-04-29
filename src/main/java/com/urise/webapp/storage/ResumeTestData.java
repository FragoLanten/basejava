package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume testResume = new Resume("Rizin Vadim");

        Map<String, String> contacts = new HashMap<>();
        contacts.put("Телефон", "8-800-500-31-02");
        contacts.put("Электронная почта", "rizinvadim@gmail.com");
        testResume.setContacts(contacts);

        Map<SectionType, AbstractSection> sections = new HashMap<>();

        AbstractSection positionSection = new TextSection(
                "Java Стажер");
        sections.put(SectionType.OBJECTIVE, positionSection);

        AbstractSection personalSection = new TextSection(
                "Нахожусь в начале пути изучения Java, поглощаю массу новых знаний, прохожу стажировку");
        sections.put(SectionType.PERSONAL, personalSection);

        List<String> achievementList = new ArrayList<>();
        achievementList.add("Прошёл курс JavaRush");
        achievementList.add("Прошёл курс Stepik \"Java для Чайников\"");
        AbstractSection achievementSection = new ListSection(achievementList);
        sections.put(SectionType.ACHIEVEMENT, achievementSection);

        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("Java Core, Collections, Generics, Maven, Spring, MVC");
        qualificationList.add("SQL, MySQL, PostegreSQL");
        AbstractSection qualificationSection = new ListSection(qualificationList);
        sections.put(SectionType.QUALIFICATIONS, qualificationSection);

        Period periodOfWork1 = new Period(LocalDate.of(2023, 2, 10), LocalDate.now(), "Программиcт Стажер", "Создание базы резюме");
        List<Period> listOfWorkPeriods = new ArrayList<>();
        listOfWorkPeriods.add(periodOfWork1);
        AbstractSection experienceSection = new CompanySection(listOfWorkPeriods, "baseJava", "topJava.ru");
        sections.put(SectionType.EXPERIENCE, experienceSection);

        Period periodOfStudy1 = new Period(LocalDate.of(2007, 9, 1), LocalDate.of(2012, 6, 30),
                "Экономист Бухгалтер", "дневное обучение");
        List<Period> listOfStudyPeriods = new ArrayList<>();
        listOfStudyPeriods.add(periodOfStudy1);
        AbstractSection studySection = new CompanySection(listOfStudyPeriods, "МАТИ РГТУ", "https://mai.ru/");
        sections.put(SectionType.EDUCATION, studySection);

        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        for (Map.Entry<SectionType, AbstractSection> section : sections.entrySet()) {
            System.out.println(section.getKey().getTitle() + " " + section.getValue());
        }
    }
}
