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
@DiscriminatorValue("EMPR")
@Table(name = "employeur")
public class Employeur extends Utilisateur {

   
   @Column(name = "secteur") 
    private String secteur;
   @Column(name = "entreprise") 
    private String entreprise;
    
      @OneToMany(mappedBy="employeur",fetch=FetchType.LAZY)
      @Cascade(CascadeType.ALL)
	private Collection<Publication> publications;

      

    public Employeur() {
		super();
	}

	public Employeur(String login, String password, String nom, String prenom, String telephone, String email,
			String pays, String adresse, String secteur, String entreprise, Collection<Publication> publications) {
		super(login, password, nom, prenom, telephone, email, pays, adresse);
		this.secteur = secteur;
		this.entreprise = entreprise;
		this.publications = publications;
	}

	public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public Collection<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Collection<Publication> publications) {
        this.publications = publications;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
    
    
      
 
    
}
