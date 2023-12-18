package com.coltla.spring6restmvc.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.coltla.spring6restmvc.models.Beer;
import com.coltla.spring6restmvc.models.BeerType;

public class BeerServiceImpl implements BeerService {

    @Override
    public Beer getBeerById(UUID id) {
        return Beer.builder()
            .id(id)
            .version(1)
            .name("Galaxy Cat")
            .type(BeerType.PALE_ALE)
            .upc("12356")
            .price(new BigDecimal("12.99"))
            .quantityOnHand(122)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

}
