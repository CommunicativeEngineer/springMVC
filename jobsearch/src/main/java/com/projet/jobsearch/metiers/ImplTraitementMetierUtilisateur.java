package com.projet.jobsearch.metiers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.jobsearch.entities.Administrateur;
import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.entities.Employe;
import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Publication;
import com.projet.jobsearch.entities.Utilisateur;
import com.projet.jobsearch.repository.AdministrateurRepository;
import com.projet.jobsearch.repository.CompetenceRepository;
import com.projet.jobsearch.repository.PublicationRepository;
import com.projet.jobsearch.repository.UtilisateurRepository;

@Service
@Transactional
public class ImplTraitementMetierUtilisateur implements ITraitementMetierUtilisateur{


	@Autowired
	private AdministrateurRepository adminisatrateurRepository;
	@Autowired
	private PublicationRepository publicationRepository;
	@Autowired
	private CompetenceRepository competenceRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository; 


	@Override
	public boolean verifierLogin(String login) {
		List<Utilisateur> list;
		list=utilisateurRepository.findByLogin(login);
		if(list.isEmpty()) return true;
		return false;
	}
	
	@Override
	public boolean verifierLoginAdm(String login) {
		List<Administrateur> list;
		list=adminisatrateurRepository.findByLogin(login);
		if(list.isEmpty()) return true;
		return false;
	}
	
	@Override
	public boolean saveEmploye(Employe employe)
	{
		if(verifierLogin(employe.getLogin())&&verifierLoginAdm(employe.getLogin()))
		{
			utilisateurRepository.save(employe);
			return true;
		}
		return false;
	}

	@Override
	public boolean saveEmployeur(Employeur employeur) {
		if(verifierLogin(employeur.getLogin())&&verifierLoginAdm(employeur.getLogin()))
		{
			utilisateurRepository.save(employeur);
			return true;
		}
		return false;
	} 
	
	@Override
	public String authLogin(String log,String pass) {
		Utilisateur user=null;
				user=utilisateurRepository.findByLoginAndPassword(log, pass);
		/*if(user != null) 
			{if(utilisateurRepository.typeUser(log, pass).equals("EMPL"))
			return "EMPL";
			if(utilisateurRepository.typeUser(log, pass).equals("EMPR"))
				return "EMPR";}*/
		if(user != null)return "user";
		Administrateur admin=adminisatrateurRepository.findByLoginAndPassword(log, pass);
		if(admin != null) return "admin";
		return "notFound";
	} 
	
	@Override
	public Utilisateur authUser(String log,String pass) {
		return utilisateurRepository.findByLoginAndPassword(log, pass);
	}

	public Employeur getEmployeur(Long id) {
		return (Employeur) utilisateurRepository.findOne(id);
	}
	
	public Employe getEmploye(Long id) {
		return (Employe) utilisateurRepository.findOne(id);
	}

	

	public void saveEmployeurWithoutVer(Employeur empr) {
		utilisateurRepository.save(empr);	
	}

	@Override
	public void saveEmployeWithoutVer(Employe empr) {
		utilisateurRepository.save(empr);
		
	}

	
}
