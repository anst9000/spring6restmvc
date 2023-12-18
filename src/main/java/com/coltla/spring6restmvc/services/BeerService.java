package com.coltla.spring6restmvc.services;

import java.util.UUID;

import com.coltla.spring6restmvc.models.Beer;

public interface BeerService {
    public Beer getBeerById(UUID id);    
}
