package com.test.swipe.data.entity;

import java.util.List;

public class CountryEntity {

    private String name;
    private List<Currency> currencies;
    private List<Language> languages;

    public String getName() {
        return name;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public static class Currency {
        private String name;

        public String getName() {
            return name;
        }
    }

    public static class Language {
        private String name;

        public String getName() {
            return name;
        }
    }

}
