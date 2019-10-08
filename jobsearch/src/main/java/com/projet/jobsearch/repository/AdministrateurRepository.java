package com.projet.jobsearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.jobsearch.entities.Administrateur;
import com.projet.jobsearch.entities.Utilisateur;


public interface AdministrateurRepository extends JpaRepository<Administrateur, Long>{
	public List<Administrateur> findByLogin(String login);
    public Administrateur findByLoginAndPassword(String login, String password);

}

