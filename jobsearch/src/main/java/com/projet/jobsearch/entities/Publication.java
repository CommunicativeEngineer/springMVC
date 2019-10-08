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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "publication")
public class Publication  {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "titre")
    private String titre;
    @Column(name = "description") 
    private String description;
    @Column(name = "date")
    private String date;
    @Column(name = "pays") 
    private String pays;
    @Column(name = "adresse") 
    private String adresse;
    @Column(name = "isactive")
    private int isactive;
    
    @OneToMany(mappedBy="publication",fetch=FetchType.LAZY)
    @Cascade(CascadeType.ALL) 
	private Collection<Competence> competences;
    
    
	@OneToMany( mappedBy="publication",fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Collection<EmplPub> emplpubs;
  
     
	 
	@ManyToOne
	@JoinColumn(name="CODE_EMPR")
	@Cascade(CascadeType.ALL)
	private Employeur employeur;

    public Publication() {
    }

  
    
    
    public Publication(String titre, String description, String date, String pays,String adresse, int isactive,
			Collection<Competence> competences, Employeur employeur) {
		super();
		this.titre = titre;
		this.description = description;
		this.date = date;
		this.pays = pays;
		this.adresse = adresse;
		this.isactive = isactive;
		this.competences = competences;
		this.employeur = employeur;
	}




	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    
    

    public String getAdresse() {
		return adresse;
	}




	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}




	public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public Employeur getEmployeur() {
        return employeur;
    }

    public void setEmployeur(Employeur employeur) {
        this.employeur = employeur;
    }

	public Collection<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Collection<Competence> competences) {
		this.competences = competences;
	}

   
    
}
