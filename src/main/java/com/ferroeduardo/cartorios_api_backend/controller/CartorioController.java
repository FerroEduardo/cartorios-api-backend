package com.ferroeduardo.cartorios_api_backend.controller;

import com.ferroeduardo.cartorios_api_backend.entity.Cartorio;
import com.ferroeduardo.cartorios_api_backend.service.CartorioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = {"*"})
public class CartorioController {

    private final Logger logger;

    private final CartorioService cartorioService;

    public CartorioController(CartorioService cartorioService) {
        this.cartorioService = cartorioService;
        this.logger = LoggerFactory.getLogger(CartorioController.class);
    }

    @GetMapping(path = "/cartorios")
    public ResponseEntity<Iterable<Cartorio>> getCartorios() {
        Iterable<Cartorio> cartorios = cartorioService.findAll();
        logger.info("Todos os cartorios foram requisitados");
        return ResponseEntity.ok().body(cartorios);
    }

    @GetMapping(path = "/cartorios/{id}")
    public ResponseEntity<Cartorio> getCartorios(@PathVariable(name = "id", required = true) Long id) {
        Cartorio cartorio = cartorioService.findById(id);
        logger.info(String.format("Cartorios requisitados pelo ID:'%d'", id));
        return ResponseEntity.ok().body(cartorio);
    }

    @GetMapping(path = "/cartorios/cnpj")
    public ResponseEntity<Iterable<Cartorio>> getCartoriosByCnpj(@RequestParam(name = "valor", required = true) String cnpj) {
        Iterable<Cartorio> cartorios = cartorioService.findByCnpjIgnoreCase(cnpj);
        logger.info(String.format("Cartorios requisitados pelo CNPJ:'%s'", cnpj));
        return ResponseEntity.ok().body(cartorios);
    }

    @PostMapping(path = "/cartorios")
    public ResponseEntity<Cartorio> saveCartorio(@RequestBody(required = true) Cartorio cartorio) {
        Cartorio cartorioSave = cartorioService.save(cartorio);
        logger.info(String.format("Cartorio ID:'%d' sofreu alterações via POST", cartorioSave.getId()));
        return ResponseEntity.ok().body(cartorioSave);
    }

    @PutMapping(path = "/cartorios/{id}")
    public ResponseEntity<Cartorio> updateCartorio(@PathVariable(name = "id", required = true) Long id, @RequestBody(required = true) Cartorio cartorioRequest) {
        Cartorio cartorio = cartorioService.updateCartorio(id, cartorioRequest);
        logger.info(String.format("Cartorio ID:'%d' sofreu alterações via PUT", id));
        return ResponseEntity.ok().body(cartorio);
    }

    @DeleteMapping(path = "/cartorios/{id}")
    public ResponseEntity<?> deleteCartorio(@PathVariable Long id) {
        cartorioService.deleteById(id);
        logger.info(String.format("Cartorio ID:'%d' apagado via DELETE", id));
        return ResponseEntity.ok().build();
    }

}
