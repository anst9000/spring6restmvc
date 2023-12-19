package com.coltla.spring6restmvc.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coltla.spring6restmvc.models.Beer;
import com.coltla.spring6restmvc.models.BeerType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Autowired
    private ObjectMapper objectMapper;

    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer galaxyCat = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Galaxy Cat")
                .type(BeerType.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Beer crank = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Crank")
                .type(BeerType.GOSE)
                .upc("12357")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Beer sunshineCity = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Sunshine City")
                .type(BeerType.IPA)
                .upc("12358")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        beerMap.put(galaxyCat.getId(), galaxyCat);
        beerMap.put(crank.getId(), crank);
        beerMap.put(sunshineCity.getId(), sunshineCity);
    }

    @Override
    public List<Beer> getBeers() {
        log.debug("--> Inside BeerServiceImpl - getBeers()");

        return new ArrayList<Beer>(beerMap.values());
    }

    @Override
    public Beer getBeerById(UUID id) {
        log.debug("--> Inside BeerServiceImpl - getBeerById() - ID = " + id.toString());

        return beerMap.get(id);
    }

    @Override
    public Beer createBeer(Beer beer) {
        log.debug("--> Inside BeerServiceImpl - saveNewBeer() - Beer = " + beer.toString());

        Beer createdBeer = Beer.builder()
                .id(UUID.randomUUID())
                .version(beer.getVersion())
                .name(beer.getName())
                .type(beer.getType())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        beerMap.put(createdBeer.getId(), createdBeer);

        return createdBeer;
    }

    @Override
    public void updateBeer(UUID id, Beer beer) {
        Beer existingBeer = beerMap.get(id);
        existingBeer.setVersion(beer.getVersion());
        existingBeer.setName(beer.getName());
        existingBeer.setType(beer.getType());
        existingBeer.setUpc(beer.getUpc());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        existingBeer.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void deleteBeer(UUID id) {
        beerMap.remove(id);
    }
}
