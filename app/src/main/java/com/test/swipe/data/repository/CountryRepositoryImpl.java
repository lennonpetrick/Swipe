package com.test.swipe.data.repository;

import com.test.swipe.data.datasource.CountryDataSource;
import com.test.swipe.data.entity.CountryEntity;
import com.test.swipe.domain.CountryRepository;

import java.util.List;

import io.reactivex.Single;

public class CountryRepositoryImpl implements CountryRepository {

    private CountryDataSource mDataSource;

    public CountryRepositoryImpl(CountryDataSource dataSource) {
        this.mDataSource = dataSource;
    }

    @Override
    public Single<List<CountryEntity>> get() {
        return mDataSource.get();
    }
}
