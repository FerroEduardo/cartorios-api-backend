package com.ferroeduardo.cartorios_api_backend.controller;

import com.ferroeduardo.cartorios_api_backend.entity.Cartorio;
import com.ferroeduardo.cartorios_api_backend.service.CartorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = {"*"})
@Api(value = "Cartorio")
public class CartorioController {

    private final Logger logger;

    private final CartorioService cartorioService;

    public CartorioController(CartorioService cartorioService) {
        this.cartorioService = cartorioService;
        this.logger = LoggerFactory.getLogger(CartorioController.class);
    }

    @ApiOperation(value = "Obter todos os catórios")
    @ApiImplicitParam(name = "api_key", value = "API Key", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    @GetMapping(path = "/cartorios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cartorio>> getCartorios() {
        List<Cartorio> cartorios = (List<Cartorio>) cartorioService.findAll();
        logger.info("Todos os cartorios foram requisitados");
        return ResponseEntity.ok().body(cartorios);
    }

    @ApiOperation(value = "Obter cartório por ID")
    @ApiImplicitParam(name = "api_key", value = "API Key", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    @GetMapping(path = "/cartorios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cartorio> getCartorios(@PathVariable(name = "id", required = true) Long id) {
        Cartorio cartorio = cartorioService.findById(id);
        logger.info(String.format("Cartorios requisitados pelo ID:'%d'", id));
        return ResponseEntity.ok().body(cartorio);
    }

    @ApiOperation(value = "Obter cartório por CNPJ")
    @ApiImplicitParam(name = "api_key", value = "API Key", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    @GetMapping(path = "/cartorios/cnpj", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cartorio>> getCartoriosByCnpj(@RequestParam(name = "valor", required = true) String cnpj) {
        List<Cartorio> cartorios = (List<Cartorio>) cartorioService.findByCnpjIgnoreCase(cnpj);
        logger.info(String.format("Cartorios requisitados pelo CNPJ:'%s'", cnpj));
        return ResponseEntity.ok().body(cartorios);
    }

    @ApiOperation(value = "Salvar cartório no banco de dados", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "api_key", value = "API Key", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    @PostMapping(path = "/cartorios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cartorio> saveCartorio(@RequestBody(required = true) Cartorio cartorio) {
        Cartorio cartorioSave = cartorioService.save(cartorio);
        logger.info(String.format("Cartorio ID:'%d' sofreu alterações via POST", cartorioSave.getId()));
        return ResponseEntity.ok().body(cartorioSave);
    }

    @ApiOperation(value = "Atualizar cartório", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "api_key", value = "API Key", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    @PutMapping(path = "/cartorios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cartorio> updateCartorio(@PathVariable(name = "id", required = true) Long id, @RequestBody(required = true) Cartorio cartorioRequest) {
        Cartorio cartorio = cartorioService.updateCartorio(id, cartorioRequest);
        logger.info(String.format("Cartorio ID:'%d' sofreu alterações via PUT", id));
        return ResponseEntity.ok().body(cartorio);
    }

    @ApiOperation(value = "Remover cartório do banco de dados")
    @ApiImplicitParam(name = "api_key", value = "API Key", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    @DeleteMapping(path = "/cartorios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCartorio(@PathVariable(required = true) Long id) {
        cartorioService.deleteById(id);
        logger.info(String.format("Cartorio ID:'%d' apagado via DELETE", id));
        return ResponseEntity.ok().build();
    }

}
