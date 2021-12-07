package com.romanova.bd.entity.enums;

public enum OriginalCertificateAvailability {
    YES("да"), NO("нет");

    private final String choice;
    OriginalCertificateAvailability(String choice) {
        this.choice = choice;
    }


    @Override
    public String toString() {
        return choice;
    }
}
