package com.romanova.bd.entity.enums;

public enum MedicalCertificate {
    YES("да"), NO("нет");

    private final String choice;

    MedicalCertificate(String choice) {
        this.choice = choice;
    }


    @Override
    public String toString() {
        return choice;
    }
}
