package com.projet.jobsearch.metiers;

import java.util.List;

import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Utilisateur;

public interface ITraitementEmployeur {
 
	public List<Employeur> employeurList ();
	public Employeur employeurInfo(long id);
	public void deleteEmployeur(int id);
}
