package com.test.swipe.data.datasource;

import com.test.swipe.data.entity.CountryEntity;

import java.util.List;

import io.reactivex.Single;

public interface CountryDataSource {
    /**
     * Fetch a list of countries from Cloud
     *
     * @return A list of {@link CountryEntity}
     * */
    Single<List<CountryEntity>> get();
}
