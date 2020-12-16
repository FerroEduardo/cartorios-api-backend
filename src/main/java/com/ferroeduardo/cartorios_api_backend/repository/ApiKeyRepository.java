package com.ferroeduardo.cartorios_api_backend.repository;

import com.ferroeduardo.cartorios_api_backend.entity.ApiKey;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApiKeyRepository extends CrudRepository<ApiKey, Long> {

    Optional<ApiKey> findByUserId(Long userId);

    Optional<ApiKey> findByKey(String apiKey);

    boolean existsByUserId(Long userId);
}
