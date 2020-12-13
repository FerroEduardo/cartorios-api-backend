package com.ferroeduardo.cartorios_api_backend.repository;

import com.ferroeduardo.cartorios_api_backend.entity.Cartorio;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartorioRepository extends CrudRepository<Cartorio, Long> {

    List<Cartorio> findAll(Example<Cartorio> cartorioExample);

    List<Cartorio> findAll(Example<Cartorio> cartorioExample, Pageable pageable);

    List<Cartorio> findAll(Pageable pageable);

    List<Cartorio> findByUfIgnoreCase(String uf);

    List<Cartorio> findByCnpjIgnoreCase(String cnpj);

    List<Cartorio> findByCnpjContainsIgnoreCase(String cnpj);

    List<Cartorio> findByNomeOficialIgnoreCase(String nomeOficial);

    List<Cartorio> findByNomeOficialContainsIgnoreCase(String nomeOficial);

    List<Cartorio> findByNomeFantasiaIgnoreCase(String nomeFantasia);

    List<Cartorio> findByNomeFantasiaContainsIgnoreCase(String nomeFantasia);

    List<Cartorio> findByEnderecoIgnoreCase(String endereco);

    List<Cartorio> findByEnderecoContainsIgnoreCase(String endereco);

    List<Cartorio> findByMunicipioIgnoreCase(String municipio);

    List<Cartorio> findByMunicipioContainsIgnoreCase(String municipio);

    List<Cartorio> findByCep(String cep);

    List<Cartorio> findByNomeDoTitularIgnoreCase(String nomeDoTitular);

    List<Cartorio> findByNomeDoTitularContainsIgnoreCase(String nomeDoTitular);

    List<Cartorio> findByNomeDoSubstitutoIgnoreCase(String nomeDoSubstituto);

    List<Cartorio> findByNomeDoSubstitutoContainsIgnoreCase(String nomeDoSubstituto);

    List<Cartorio> findByNomeDoJuizIgnoreCase(String nomeDoJuiz);

    List<Cartorio> findByNomeDoJuizContainsIgnoreCase(String nomeDoJuiz);

    List<Cartorio> findByHomepageIgnoreCase(String homepage);

    List<Cartorio> findByHomepageContainsIgnoreCase(String homepage);

    List<Cartorio> findByEmailIgnoreCase(String email);

    List<Cartorio> findByEmailContainsIgnoreCase(String email);

    List<Cartorio> findByTelefoneIgnoreCase(String telefone);

    List<Cartorio> findByTelefoneContainsIgnoreCase(String telefone);

    List<Cartorio> findByFaxIgnoreCase(String fax);

    List<Cartorio> findByFaxContainsIgnoreCase(String fax);
}
