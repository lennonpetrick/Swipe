package com.test.swipe.data.entity.mapper;

import com.google.gson.reflect.TypeToken;
import com.test.swipe.data.entity.CountryEntity;
import com.test.swipe.shared.GsonSingleton;

import java.lang.reflect.Type;
import java.util.List;

public class JsonMapper {

    public static List<CountryEntity> transformCountryEntities(String json) {
        Type type = new TypeToken<List<CountryEntity>>(){}.getType();
        return GsonSingleton.getGson().fromJson(json, type);
    }

}
