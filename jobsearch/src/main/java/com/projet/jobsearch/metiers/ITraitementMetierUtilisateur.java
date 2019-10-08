package com.projet.jobsearch.metiers;

import java.util.ArrayList;

import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.entities.Employe;
import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Publication;
import com.projet.jobsearch.entities.Utilisateur;

public interface ITraitementMetierUtilisateur {
	
	
	public Employeur getEmployeur(Long id);
	
	public Employe getEmploye(Long id);
	
	Utilisateur authUser(String log, String pass);
	
	public boolean verifierLogin(String login);

	boolean saveEmploye(Employe employe);
	
	boolean saveEmployeur(Employeur employeur);

	boolean verifierLoginAdm(String login);

	String authLogin(String log, String pass);
	
	public void saveEmployeurWithoutVer(Employeur empr);
	
	public void saveEmployeWithoutVer(Employe empr);

	
	
}
