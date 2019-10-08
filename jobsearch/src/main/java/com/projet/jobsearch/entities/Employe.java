/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.jobsearch.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@DiscriminatorValue("EMPL")
@Table(name = "employe")
public class Employe extends Utilisateur {

   
    @Column(name = "domaine")
    @NotEmpty
    private String domaine;

     @OneToMany(mappedBy="employe",fetch=FetchType.LAZY)
     @Cascade(CascadeType.ALL)
 	private Collection<Competence> competences;
 
     @OneToMany(mappedBy="employe",fetch=FetchType.LAZY)
     @Cascade(CascadeType.ALL)
 	private Collection<EmplPub> emplpubs;
     
     
    public Employe(String login, String password, String nom, String prenom, String telephone, String email,
			String pays, String adresse, String domaine, Collection<Competence> competences) {
		super(login, password, nom, prenom, telephone, email, pays, adresse);
		this.domaine = domaine;
		this.competences = competences;
	}
    
    

	public Employe() {
		super();
	}



	public Collection<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Collection<Competence> competences) {
		this.competences = competences;
	}

	public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }


    
   
}
