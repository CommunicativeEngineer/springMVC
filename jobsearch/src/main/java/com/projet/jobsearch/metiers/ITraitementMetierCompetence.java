package com.projet.jobsearch.metiers;

import java.util.ArrayList;

import com.projet.jobsearch.entities.Competence;

public interface ITraitementMetierCompetence {
	
	public void saveCompetences(ArrayList<Competence> c);

public ArrayList<Competence> returnComp(Long id);
	
	public void deleteComps(Long id);
}
