package com.ferroeduardo.cartorios_api_backend.controller;

import com.ferroeduardo.cartorios_api_backend.entity.Cartorio;
import com.ferroeduardo.cartorios_api_backend.service.CartorioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher;
import static org.springframework.data.domain.ExampleMatcher.matching;

@RestController
@RequestMapping(path = "/api/frontend")
public class FrontController {

    private final Logger logger;

    private final CartorioService cartorioService;

    public FrontController(CartorioService cartorioService) {
        this.logger = LoggerFactory.getLogger(FrontController.class);
        this.cartorioService = cartorioService;
    }

    @PostMapping(path = "/cartorios")
    public ResponseEntity<Iterable<Cartorio>> getCartorios(@RequestBody Cartorio cartorio,
                                                           @RequestParam(required = false, defaultValue = "0", name = "page") int page,
                                                           @RequestParam(required = false, defaultValue = "20", name = "cartoriosTableRows") int cartoriosTableRows) {
        ExampleMatcher exampleMatcher = matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

        Example<Cartorio> cartorioExample = Example.of(cartorio, exampleMatcher);
        Iterable<Cartorio> cartorios = cartorioService.findAll(cartorioExample, PageRequest.of(page, cartoriosTableRows));
        logger.info("Cartorios requisitados pelo frontend foram processados");
        return ResponseEntity.ok().body(cartorios);
    }
}
