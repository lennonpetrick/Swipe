package com.test.swipe.domain;

import com.test.swipe.data.entity.CountryEntity;

import java.util.List;

import io.reactivex.Single;

public interface CountryRepository {
    Single<List<CountryEntity>> get();
}
