package com.projet.jobsearch.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.entities.EmplPub;
import com.projet.jobsearch.entities.Employe;
import com.projet.jobsearch.entities.Publication;

public interface EmplPubRepository extends JpaRepository<EmplPub, Long>{

	@Query("select p from EmplPub p where p.employe.id=:x ORDER BY p.id DESC")
	public ArrayList<EmplPub> listPubs(@Param("x") Long id);
	

	@Query("select p from EmplPub p where p.date like %:x% and p.employe.id=:y")
	public List<EmplPub> findByDateEP(@Param("x")String enregistrer,@Param("y")Long id);
	
	@Query("select p from EmplPub p where p.publication.titre like %:x% and p.employe.id=:y")
	public List<EmplPub> findByTitre(@Param("x")String enregistrer,@Param("y")Long id);
	
	@Query("select p from EmplPub p where p.publication.date like %:x% and p.employe.id=:y")
	public List<EmplPub> findByDate(@Param("x")String enregistrer,@Param("y")Long id);
	
	@Query("select p from EmplPub p where p.publication.pays like %:x% and p.employe.id=:y")
	public List<EmplPub> findByPays(@Param("x")String enregistrer,@Param("y")Long id);
	
	@Query("select p from EmplPub p where p.publication.adresse like %:x% and p.employe.id=:y")
	public List<EmplPub> findByAdresse(@Param("x")String enregistrer,@Param("y")Long id);
	
	@Query("select p from EmplPub p where p.publication.description like %:x% and p.employe.id=:y")
	public List<EmplPub> findByDescription(@Param("x")String enregistrer,@Param("y")Long id);
	
	@Query("select ep from EmplPub ep, Competence c where c.nom like %:x% and ep.employe.id=:y and ep.publication.id=c.publication.id")
	public List<EmplPub> findByCompetence(@Param("x")String enregistrer,@Param("y")Long id);

}
