package com.urise.webapp.storage;

import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;

import java.util.HashMap;
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
        AbstractSection personalSection = new TextSection(
                "Нахожусь в начале пути изучения Java, поглощаю массу новых знаний, прохожу стажировку");
        sections.put(SectionType.OBJECTIVE, positionSection);
        sections.put(SectionType.PERSONAL, personalSection);

    }
}
