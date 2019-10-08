package com.projet.jobsearch.metiers;

import java.util.ArrayList;
import java.util.List;

import com.projet.jobsearch.entities.Publication;

public interface ITraitementMetierPublication {
	ArrayList<Publication> listLastPub();
	
	ArrayList<Publication> listLastPubEmpr(Long id);

	public void savePub(Publication pub);
	
	public void deletePub(Long id);
	
	public void updatePub(Publication pub);
	
	public Publication getPub(Long id);
	
	public ArrayList<Publication> listPubsAll(String enregistrer);

	public ArrayList<Publication> listPubs(String enregistrer,Long id);
 


public Publication publicationInfo(int id);
	
	public List<Publication> publicationList ();
	
	public void publicationAjout(Publication publication);
	
	public void deletePublication(long id);
	 
	
}
