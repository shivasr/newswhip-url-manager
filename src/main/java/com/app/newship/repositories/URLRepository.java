package com.app.newship.repositories;

import com.app.newship.entities.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface URLRepository extends JpaRepository<URLEntity, UUID> {

    List<URLEntity> findAll();

    URLEntity findByURL(String URL);

    URLEntity save(URLEntity urlEntity);

    void deleteByUuid(UUID uuid);

}
