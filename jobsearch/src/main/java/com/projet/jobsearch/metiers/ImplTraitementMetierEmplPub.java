package com.projet.jobsearch.metiers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.jobsearch.entities.EmplPub;
import com.projet.jobsearch.entities.Employe;
import com.projet.jobsearch.repository.AdministrateurRepository;
import com.projet.jobsearch.repository.CompetenceRepository;
import com.projet.jobsearch.repository.EmplPubRepository;
import com.projet.jobsearch.repository.PublicationRepository;
import com.projet.jobsearch.repository.UtilisateurRepository;

@Service
@Transactional
public class ImplTraitementMetierEmplPub implements ITraitementMetierEmplPub{
	
	@Autowired
	private EmplPubRepository emplPubRepository;
	
	
	@Override
	public void saveProfilEmpl(EmplPub ep) {
		emplPubRepository.save(ep);
		
	}


	@Override
	public ArrayList<EmplPub> listPubs(Long id) {
		return emplPubRepository.listPubs(id);
	}


	@Override
	public void deletePub(Long id_pub) {
		emplPubRepository.delete(id_pub);
		
	}
	
	@Override
	public ArrayList<EmplPub> listPubs2(String enregistrer, Long id) {
		ArrayList<EmplPub>one= (ArrayList<EmplPub>) emplPubRepository.findByDateEP(enregistrer,id);
		ArrayList<EmplPub>two= (ArrayList<EmplPub>) emplPubRepository.findByTitre(enregistrer,id);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((EmplPub) x);
			}
		two= (ArrayList<EmplPub>) emplPubRepository.findByDate(enregistrer,id);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((EmplPub) x);
			}
		two= (ArrayList<EmplPub>) emplPubRepository.findByAdresse(enregistrer,id);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((EmplPub) x);
			}
		two= (ArrayList<EmplPub>) emplPubRepository.findByPays(enregistrer,id);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((EmplPub) x);
			}
		two= (ArrayList<EmplPub>) emplPubRepository.findByDescription(enregistrer,id);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((EmplPub) x);
			}
		two= (ArrayList<EmplPub>) emplPubRepository.findByCompetence(enregistrer,id);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((EmplPub) x);
			}
		return one;
	}
	

	

}
