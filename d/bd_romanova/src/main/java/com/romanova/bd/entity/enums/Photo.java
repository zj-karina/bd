package com.romanova.bd.entity.enums;

public enum Photo {
    YES("да"), NO("нет");

    private final String choice;

    Photo(String choice) {
        this.choice = choice;
    }


    @Override
    public String toString() {
        return choice;
    }
}
