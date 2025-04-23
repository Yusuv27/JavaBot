package org.example.enumOption;

public enum OptionEnum {
    GET_TIME("GetTime"),
    DEPOSIT_CALCULATE("DepositCalculate"),
    CREDIT_CALCULATE("CreditCalculate"),
    UNKNOW("Unknow");

    private final String name;

    OptionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
