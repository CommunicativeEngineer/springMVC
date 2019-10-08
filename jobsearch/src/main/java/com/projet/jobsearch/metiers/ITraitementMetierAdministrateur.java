package com.projet.jobsearch.metiers;

import java.util.List;


import com.projet.jobsearch.entities.Administrateur;

public interface ITraitementMetierAdministrateur {

	Administrateur authAdmin(String log, String pass);
	List<Administrateur> trouverParLogin(String login);

	public Administrateur adminInfo(int id);
	
	public List<Administrateur> adminList ();
	
	public void adminAjout(Administrateur administrateur);
	
	public void deleteAdmin(int id); 
}
