package com.projet.jobsearch.metiers;

import java.util.List;

import com.projet.jobsearch.entities.Administrateur;

public interface ITraitementStatistiques {

	public int countPublication();
	public int countEmployeur();
	public int countEmploye();
	public int countPublicationActive();
	public float pourcentageEmploye();
}
