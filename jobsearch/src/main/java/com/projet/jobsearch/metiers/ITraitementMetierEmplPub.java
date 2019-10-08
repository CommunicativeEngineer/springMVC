package com.projet.jobsearch.metiers;

import java.util.ArrayList;

import com.projet.jobsearch.entities.EmplPub;
import com.projet.jobsearch.entities.Employe;

public interface ITraitementMetierEmplPub {

	void saveProfilEmpl(EmplPub ep);

	ArrayList<EmplPub> listPubs(Long id);

	void deletePub(Long id_pub);
	
	ArrayList<EmplPub> listPubs2(String enregistrer, Long id);

}
