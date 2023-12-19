package com.coltla.spring6restmvc.services;

import java.util.List;
import java.util.UUID;

import com.coltla.spring6restmvc.models.Beer;

public interface BeerService {
    public List<Beer> getBeers();

    public Beer getBeerById(UUID id);

    public Beer createBeer(Beer beer);

    public void updateBeer(UUID id, Beer beer);

    public void deleteBeer(UUID id);
}
