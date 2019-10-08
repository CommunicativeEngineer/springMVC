package com.projet.jobsearch.metiers;

import java.util.ArrayList;
import java.util.Collection;

import com.projet.jobsearch.entities.Administrateur;
import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.entities.Employe;
import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Utilisateur;

public interface ITraitementMetier {

	public boolean verifierLogin(String login);

	boolean saveEmploye(Employe employe);
	
	boolean saveEmployeur(Employeur employeur);

	boolean verifierLoginAdm(String login);

	String authLogin(String log, String pass);

	Administrateur authAdmin(String log, String pass);

	Utilisateur authUser(String log, String pass);

	void saveCompetences(ArrayList<Competence> c);

}
