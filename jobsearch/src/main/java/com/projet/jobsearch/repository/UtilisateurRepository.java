package com.projet.jobsearch.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.jobsearch.entities.Utilisateur;


public interface UtilisateurRepository extends BaseParentDAO<Utilisateur>,  JpaRepository<Utilisateur, Long>{

	List<Utilisateur> findByLogin(String login);
    public Utilisateur findByLoginAndPassword(String login, String password);
   /* @Query("select type_user from Utilisateur u where u.utilisateur.login=:x and u.utilisateur.password=:y")
	public String typeUser(@Param("x")String login,@Param("y")String password);*/
    /*@Query(value ="select u  from Utilisateur u where u.type_user like %:EMPR%") 
    List<Utilisateur> findEmployeur(@Param("EMPR") String EMPR); */
   // List<Utilisateur> findByTypeUser(String typeUser);
    
}
