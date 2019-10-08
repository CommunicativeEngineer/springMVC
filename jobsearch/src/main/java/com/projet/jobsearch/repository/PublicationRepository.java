package com.projet.jobsearch.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.entities.Publication;
import com.projet.jobsearch.entities.Utilisateur;

public interface PublicationRepository extends JpaRepository<Publication, Long>{

	@Query("select p from Publication p ORDER BY id DESC")
	public List<Publication> listLastPub();
	
	/*@Query("select p from Publication p where p.utilisateur.id=:x ORDER BY id DESC")
	public List<Publication> findByEmployeur(@Param("x")Long id);*/
	
	@Query("select p from Publication p where p.employeur.id=:x ORDER BY p.id DESC")
	public List<Publication> findPubs(@Param("x")Long id);
	
	@Query("select p from Publication p where p.titre like %:x%")
	public List<Publication> findByTitre(@Param("x")String enregistrer);
	
	@Query("select p from Publication p where p.date like %:x%")
	public List<Publication> findByDate(@Param("x")String enregistrer);
	
	@Query("select p from Publication p where p.pays like %:x%")
	public List<Publication> findByPays(@Param("x")String enregistrer);
	
	@Query("select p from Publication p where p.adresse like %:x%")
	public List<Publication> findByAdresse(@Param("x")String enregistrer);
	
	@Query("select p from Publication p where p.description like %:x%")
	public List<Publication> findByDescription(@Param("x")String enregistrer);
	 
	//Jihen
	List<Publication> findByTitreOrPays(String titre,String pays);
	List<Publication> findByCompetences(Collection<Competence> competences);
	List<Publication> findByIsactive(int isactive);
}
