package com.projet.jobsearch.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.jobsearch.entities.Employe;
import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Utilisateur;


public interface EmployeRepository extends BaseParentDAO<Employe>,  JpaRepository<Employe, Long>{
// Everything from base inherited
	}
