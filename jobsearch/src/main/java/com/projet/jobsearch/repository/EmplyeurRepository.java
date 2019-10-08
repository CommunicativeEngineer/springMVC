package com.projet.jobsearch.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Utilisateur;


public interface EmplyeurRepository extends BaseParentDAO<Employeur>,  JpaRepository<Employeur, Long>{
// Everything from base inherited
	List<Employeur> findByEntreprise(String entreprise);
	}
