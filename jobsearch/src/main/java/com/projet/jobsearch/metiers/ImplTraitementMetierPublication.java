package com.projet.jobsearch.metiers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.jobsearch.entities.EmplPub;
import com.projet.jobsearch.entities.Publication;
import com.projet.jobsearch.repository.AdministrateurRepository;
import com.projet.jobsearch.repository.CompetenceRepository;
import com.projet.jobsearch.repository.EmplPubRepository;
import com.projet.jobsearch.repository.PublicationRepository;
import com.projet.jobsearch.repository.UtilisateurRepository;

@Service
@Transactional
public class ImplTraitementMetierPublication implements ITraitementMetierPublication {

	@Autowired
	private AdministrateurRepository adminisatrateurRepository;
	@Autowired
	private PublicationRepository publicationRepository;
	@Autowired
	private CompetenceRepository competenceRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository; 
	@Autowired
	private EmplPubRepository emplPubRepository; 
	
	public ArrayList<Publication> listLastPub() {
		
		return (ArrayList<Publication>) publicationRepository.listLastPub().stream().limit(10).collect(Collectors.toList());
	}

	public ArrayList<Publication> listLastPubEmpr(Long id) {
		
		return (ArrayList<Publication>) publicationRepository.findPubs(id);
	}

	public void savePub(Publication pub) {
		publicationRepository.save(pub);
		
	}

	public void deletePub(Long id) {
		publicationRepository.delete(id);
	}

	public void updatePub(Publication pub) {
		publicationRepository.save(pub);
		
	}

	public Publication getPub(Long id) {
		return publicationRepository.findOne(id);
	}

	public ArrayList<Publication> listPubsAll(String enregistrer) {
		ArrayList<Publication>one= (ArrayList<Publication>) competenceRepository.findByCompetence(enregistrer);
		ArrayList<Publication>two= (ArrayList<Publication>) publicationRepository.findByTitre(enregistrer);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((Publication) x);
			}
		two= (ArrayList<Publication>) publicationRepository.findByDate(enregistrer);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((Publication) x);
			}
		two= (ArrayList<Publication>) publicationRepository.findByAdresse(enregistrer);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((Publication) x);
			}
		two= (ArrayList<Publication>) publicationRepository.findByPays(enregistrer);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((Publication) x);
			}
		two= (ArrayList<Publication>) publicationRepository.findByDescription(enregistrer);
		for (Object x : two){
			   if (!one.contains(x))
			      one.add((Publication) x);
			}

		return one;
	}

	public ArrayList<Publication> listPubs(String enregistrer,Long id) {
		ArrayList<Publication>one =listPubsAll(enregistrer); 
		for(int j=0;j<one.size();j++)
			System.out.print("/"+one.get(j).getTitre());
		for(int i=0;i<one.size();i++)
		{
			if(!one.get(i).getEmployeur().getId().equals(id))
			{
				one.remove(i);
			}
		}

		return one;
	}

	
	
	public Publication publicationInfo(int id)
	{
		Publication publication = publicationRepository.getOne((long) id);
		return publication;
	}
 
	public List<Publication> publicationList ()

	{	
		return (publicationRepository.findAll());
	}

	public void publicationAjout(Publication publication) {
		publicationRepository.save(publication);
	}

	public void deletePublication(long id){
	       
		Publication publication = publicationRepository.findOne(id);
		if(publication!= null) {
		publicationRepository.delete(publication);
		}
      
    }

	
}
