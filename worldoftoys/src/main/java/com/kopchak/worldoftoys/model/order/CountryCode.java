package com.kopchak.worldoftoys.model.order;

public enum CountryCode {
    UA("380");
    private final String countryCode;

    private CountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
