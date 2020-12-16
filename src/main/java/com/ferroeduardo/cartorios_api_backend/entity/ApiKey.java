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
    @SequenceGenerator(name = "cartorios_api_key_id_seq", sequenceName = "cartorios_api_key_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartorios_api_key_id_seq")
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
