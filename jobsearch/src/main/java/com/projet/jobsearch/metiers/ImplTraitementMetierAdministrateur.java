package com.projet.jobsearch.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.jobsearch.entities.Administrateur;
import com.projet.jobsearch.repository.AdministrateurRepository;
import com.projet.jobsearch.repository.CompetenceRepository;
import com.projet.jobsearch.repository.PublicationRepository;
import com.projet.jobsearch.repository.UtilisateurRepository;

@Service
@Transactional
public class ImplTraitementMetierAdministrateur implements ITraitementMetierAdministrateur {

	@Autowired
	private AdministrateurRepository adminisatrateurRepository;
	@Autowired
	private PublicationRepository publicationRepository;
	@Autowired
	private CompetenceRepository competenceRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository; 
	
	@Override
	public Administrateur authAdmin(String log,String pass) {
		return adminisatrateurRepository.findByLoginAndPassword(log, pass);
		
	}
	@Override
	public List<Administrateur> trouverParLogin(String login){ 
		System.out.println(adminisatrateurRepository.findByLogin(login).isEmpty()); 
		return adminisatrateurRepository.findByLogin(login);
	}
	 
	
	public Administrateur adminInfo(int id)
	{
		Administrateur administrateur = adminisatrateurRepository.getOne((long) id);
		return administrateur;
	}
 
	public List<Administrateur> adminList ()

	{	
		return (adminisatrateurRepository.findAll());
	}

	public void adminAjout(Administrateur administrateur) { 
		adminisatrateurRepository.save(administrateur);
	}

	public void deleteAdmin(int id){ 
        Administrateur administrateur = adminisatrateurRepository.findOne((long)id);
        adminisatrateurRepository.delete(administrateur);
      
    }

 
}
