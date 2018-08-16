package com.test.swipe.domain.model.mapper;

import com.test.swipe.data.entity.CountryEntity;
import com.test.swipe.domain.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryMapper {

    public static Country transform(CountryEntity entity) {
        if (entity == null)
            return null;

        Country model = new Country();
        model.setName(entity.getName());

        List<CountryEntity.Currency> currencies = entity.getCurrencies();
        if (currencies != null && !currencies.isEmpty()) {
            model.setMainCurrency(currencies.get(0).getName());
        }

        List<CountryEntity.Language> languages = entity.getLanguages();
        if (languages != null && !languages.isEmpty()) {
            model.setMainLanguage(languages.get(0).getName());
        }

        return model;
    }

    public static List<Country> transform(List<CountryEntity> entities) {
        if (entities == null || entities.isEmpty())
            return null;

        List<Country> models = new ArrayList<>();
        for (CountryEntity entity : entities) {
            models.add(transform(entity));
        }

        return models;
    }

}
