package com.ferroeduardo.cartorios_api_backend.service;

import com.ferroeduardo.cartorios_api_backend.entity.Cartorio;
import com.ferroeduardo.cartorios_api_backend.exception.ApiCustomException;
import com.ferroeduardo.cartorios_api_backend.exception.CartorioNotFoundException;
import com.ferroeduardo.cartorios_api_backend.repository.CartorioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartorioService {

    private final Logger logger;

    private final CartorioRepository cartorioRepository;

    public CartorioService(CartorioRepository cartorioRepository) {
        this.cartorioRepository = cartorioRepository;
        this.logger = LoggerFactory.getLogger(CartorioService.class);
    }

    public Iterable<Cartorio> findAll() {
        return cartorioRepository.findAll();
    }

    public Iterable<Cartorio> findAll(Example<Cartorio> cartorioExample) {
        return cartorioRepository.findAll(cartorioExample);
    }

    public Iterable<Cartorio> findAll(Pageable pageable) {
        return cartorioRepository.findAll(pageable);
    }

    public Iterable<Cartorio> findAll(Example<Cartorio> cartorioExample, Pageable pageable) {
        return cartorioRepository.findAll(cartorioExample, pageable);
    }

    public Cartorio findById(Long id) throws CartorioNotFoundException {
        return cartorioRepository.findById(id).orElseThrow(() -> new CartorioNotFoundException(id));
    }

    public Cartorio updateCartorio(Long id, Cartorio novo) throws CartorioNotFoundException {
        return cartorioRepository.findById(id).map(cartorio -> {
            cartorio.setUf(novo.getUf());
            cartorio.setCnpj(novo.getCnpj());
            cartorio.setNomeOficial(novo.getNomeOficial());
            cartorio.setNomeFantasia(novo.getNomeFantasia());
            cartorio.setEndereco(novo.getEndereco());
            cartorio.setBairro(novo.getBairro());
            cartorio.setMunicipio(novo.getMunicipio());
            cartorio.setCep(novo.getCep());
            cartorio.setNomeDoTitular(novo.getNomeDoTitular());
            cartorio.setNomeDoSubstituto(novo.getNomeDoSubstituto());
            cartorio.setNomeDoJuiz(novo.getNomeDoJuiz());
            cartorio.setHomepage(novo.getHomepage());
            cartorio.setEmail(novo.getEmail());
            cartorio.setTelefone(novo.getTelefone());
            cartorio.setFax(novo.getFax());
            cartorio.setHorarioDeAtendimento(novo.getHorarioDeAtendimento());
            cartorio.setAreaDeAbrangencia(novo.getAreaDeAbrangencia());
            cartorio.setAtribuicoes(novo.getAtribuicoes());
            return cartorioRepository.save(cartorio);
        }).orElseThrow(() -> new CartorioNotFoundException(id));
    }

    public Iterable<Cartorio> findByCnpjIgnoreCase(String cnpj) {
        return cartorioRepository.findByCnpjIgnoreCase(cnpj);
    }

    public Cartorio save(Cartorio cartorio) throws ApiCustomException {
        if (cartorio == null) {
            logger.warn("Cartório é null, erro ao tentar salvar no banco de dados");
            throw new ApiCustomException("O objeto enviado para salvar no banco é 'null'", HttpStatus.BAD_REQUEST);
        }
        return cartorioRepository.save(cartorio);
    }

    public void deleteById(Long id) throws CartorioNotFoundException {
        Optional<Cartorio> cartorio = cartorioRepository.findById(id);
        if (cartorio.isPresent()) {
            cartorioRepository.deleteById(id);
        } else {
            throw new CartorioNotFoundException(id);
        }
    }

    public long count() {
        return cartorioRepository.count();
    }
}
