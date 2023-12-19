package com.coltla.spring6restmvc.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coltla.spring6restmvc.models.Beer;
import com.coltla.spring6restmvc.services.BeerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private ObjectMapper objectMapper = new ObjectMapper();

    private final BeerService beerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> getBeers() {
        return beerService.getBeers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("id") UUID id) {
        log.debug("Get Beer by ID - in Controller");

        return beerService.getBeerById(id);
    }

    @PostMapping
    public ResponseEntity<String> createBeer(@RequestBody Beer beer) {
        Beer savedBeer = beerService.createBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBeer(@PathVariable("id") UUID id, @RequestBody Beer beer) {
        beerService.updateBeer(id, beer);

        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBeer(@PathVariable("id") UUID id) {
        beerService.deleteBeer(id);

        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateBeer(@PathVariable("id") UUID id, @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, JsonProcessingException {
        log.debug("--> existingBeer - in Controller = " + jsonPatch.toString());

        Beer beer = beerService.getBeerById(id);
        Beer beerPatched = applyPatchToBeer(jsonPatch, beer);

        beerService.updateBeer(id, beerPatched);

        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    private Beer applyPatchToBeer(JsonPatch jsonPatch, Beer targetBeer)
            throws JsonPatchException, JsonProcessingException {
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(targetBeer, JsonNode.class));
        return objectMapper.treeToValue(patched, Beer.class);
    }

}
