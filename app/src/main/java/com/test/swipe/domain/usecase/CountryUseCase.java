package com.test.swipe.domain.usecase;

import com.test.swipe.domain.model.Country;

import java.util.List;

import io.reactivex.Single;

public interface CountryUseCase {
    Single<List<Country>> get();
}
