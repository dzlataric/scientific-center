package com.scientific.center.repository;

import com.scientific.center.model.AreaOfScienceEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaOfScienceRepository extends JpaRepository<AreaOfScienceEntity, Long> {

	Optional<AreaOfScienceEntity> findByKey(String key);

}
