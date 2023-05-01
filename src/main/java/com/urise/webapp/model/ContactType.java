package com.urise.webapp.model;

public enum ContactType {
    TELEPHONE("Телефон"),
    EMAIL("Электронная почта"),
    SKYPE("Скайп");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
