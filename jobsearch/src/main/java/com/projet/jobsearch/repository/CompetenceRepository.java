package com.projet.jobsearch.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.entities.Publication;


public interface CompetenceRepository extends JpaRepository<Competence, Long>{

	@Query("select c from Competence c where c.publication.id=:x ")
	public ArrayList<Competence> returnComp(@Param("x")Long id);
	
	@Transactional
	 @Modifying
	@Query("delete from Competence c where c.publication.id=:x ")
	public void deleteComps(@Param("x")Long id);
	
	@Query("select c.publication from Competence c where c.nom like %:x%")
	public List<Publication> findByCompetence(@Param("x")String enregistrer);
	
	
}
