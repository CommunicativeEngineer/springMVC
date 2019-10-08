package com.projet.jobsearch.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.projet.jobsearch.entities.Utilisateur;


@NoRepositoryBean //Read-Only
public interface BaseParentDAO<EntityType extends Utilisateur> extends CrudRepository<EntityType, Long> {

    @Query("select e from #{#entityName} e") // #{#entityName} will be magically replaced by type arguments in children
    List<EntityType> findThemAll();
}
