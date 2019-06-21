package com.superhero.restrepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.superhero.domain.Mission;

@RepositoryRestResource
public interface MissionRepository extends CrudRepository<Mission, Long> {

}
