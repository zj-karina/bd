package com.romanova.bd.entity.enums;

public enum PaymentReceipt {
    YES("да"), NO("нет");

    private final String choice;

    PaymentReceipt(String choice) {
        this.choice = choice;
    }


    @Override
    public String toString() {
        return choice;
    }
}
