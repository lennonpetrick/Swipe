package com.test.swipe.domain.usecase;

import com.test.swipe.domain.CountryRepository;
import com.test.swipe.domain.model.Country;
import com.test.swipe.domain.model.mapper.CountryMapper;

import java.util.List;

import io.reactivex.Single;

public class CountryUseCaseImpl implements CountryUseCase {

    private CountryRepository mRepository;

    public CountryUseCaseImpl(CountryRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public Single<List<Country>> get() {
        return mRepository.get()
                .map(CountryMapper::transform);
    }
}
