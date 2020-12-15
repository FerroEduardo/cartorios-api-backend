package com.ferroeduardo.cartorios_api_backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "cartorios_api_key")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "api_key", length = 36, nullable = true, unique = true)
    private String key;

    public ApiKey(Long userId, String key) {
        this.userId = userId;
        this.key = key;
    }
}
