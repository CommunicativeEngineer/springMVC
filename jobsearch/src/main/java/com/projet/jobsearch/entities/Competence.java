package com.projet.jobsearch.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "competence")
public class Competence {

	@Id
    @GeneratedValue
    @Column(name = "id")
	private Long id;
	@Column(name = "nom")
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="CODE_PUB")
	@Cascade(CascadeType.ALL)
	private Publication publication;
	
	@ManyToOne
	@JoinColumn(name="CODE_EMPL")
	@Cascade(CascadeType.ALL)
	private Employe employe;
	
	public Competence(String nom) {
		super();
		this.nom = nom;
	}

	public Competence() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	
	
	
	
}
