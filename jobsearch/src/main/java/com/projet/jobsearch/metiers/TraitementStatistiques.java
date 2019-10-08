package com.projet.jobsearch.metiers;

import java.util.List;

import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.jobsearch.entities.Administrateur;
import com.projet.jobsearch.repository.AdministrateurRepository;
import com.projet.jobsearch.repository.EmployeRepository;
import com.projet.jobsearch.repository.EmplyeurRepository;
import com.projet.jobsearch.repository.PublicationRepository;
import com.projet.jobsearch.repository.UtilisateurRepository;


@SpringBootApplication
@EnableJpaRepositories("com.projet.jobsearch.repository")
public class TraitementStatistiques implements ITraitementStatistiques{

	@Autowired
	private PublicationRepository publicationRepository;
	@Autowired
	private EmplyeurRepository employeurRepository;
	@Autowired
	private EmployeRepository employeRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	public int countPublication()
	{	 
		return publicationRepository.findAll().size();
	}
	public int countEmployeur()
	{	 
		return employeurRepository.findAll().size();
	}
	public int countEmploye()
	{	 
		return employeRepository.findAll().size();
	}
	public int countUtilisateur()
	{	 
		return utilisateurRepository.findAll().size();
	}
	public int countPublicationActive() {
		return publicationRepository.findByIsactive(0).size();
	}
	public float pourcentageEmploye() {
float resultat;
		
		int nombreEmploye = publicationRepository.findByIsactive(0).size();
		int nombreUser = this.countUtilisateur();
		resultat = (nombreEmploye *100)/nombreUser;
		System.out.println(resultat);
		return resultat;
	}

}
