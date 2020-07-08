package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {

    LanguageEntity findOneByName(String name);
}
