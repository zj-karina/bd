package com.romanova.bd.entity.enums;

public enum IADocuments {
    YES("да"), NO("нет");

    private final String choice;

    IADocuments(String choice) {
        this.choice = choice;
    }


    @Override
    public String toString() {
        return choice;
    }
}
