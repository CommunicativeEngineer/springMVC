package com.projet.jobsearch.metiers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.repository.AdministrateurRepository;
import com.projet.jobsearch.repository.CompetenceRepository;
import com.projet.jobsearch.repository.PublicationRepository;
import com.projet.jobsearch.repository.UtilisateurRepository;

@Service
@Transactional
public class ImplTraitementMetierCompetence implements ITraitementMetierCompetence {

	@Autowired
	private AdministrateurRepository adminisatrateurRepository;
	@Autowired
	private PublicationRepository publicationRepository;
	@Autowired
	private CompetenceRepository competenceRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository; 
	
	
	public void saveCompetences(ArrayList<Competence> c) {
		
		for(int i=0;i<c.size();i++)
		{competenceRepository.save(c.get(i));}
		
	}
	
	public ArrayList<Competence> returnComp(Long id) {
		return competenceRepository.returnComp(id);
	}

	public void deleteComps(Long id) {
		competenceRepository.deleteComps(id);
	}
}
