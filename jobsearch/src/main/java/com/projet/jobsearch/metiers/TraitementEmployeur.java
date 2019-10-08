package com.projet.jobsearch.metiers;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Publication;
import com.projet.jobsearch.entities.Utilisateur;
import com.projet.jobsearch.repository.EmplyeurRepository;


@SpringBootApplication
@EnableJpaRepositories("com.projet.jobsearch.repository")
public class TraitementEmployeur implements ITraitementEmployeur{

	@Autowired
	private EmplyeurRepository emplyeurRepository;
	
	
	public List<Employeur> employeurList ()

	{	
		return (emplyeurRepository.findThemAll());
	}
	
	public Employeur employeurInfo(long id)
	{
		Employeur employeur = emplyeurRepository.getOne((long) id);
		return employeur;
	}
 
	public void deleteEmployeur(int id){ 
		Employeur employeur = emplyeurRepository.findOne((long)id);
		emplyeurRepository.delete(employeur);
      
    }
	 

	public Employeur rechercheEmployeur (String entreprise) { 
		List<Employeur> employeurList = emplyeurRepository.findByEntreprise(entreprise);
		Employeur employeur1 = new Employeur(); 
		employeur1 = employeurList.get(0);
		return employeur1;
	} 
}
