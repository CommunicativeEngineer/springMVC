package com.projet.jobsearch.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "emplpub")
public class EmplPub {

	@Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private String date;
    
    @ManyToOne
	@JoinColumn(name="CODE_PUB")
    @Cascade(CascadeType.ALL)
	private Publication publication;
    
    @ManyToOne
	@JoinColumn(name="CODE_EMPL")
    @Cascade(CascadeType.ALL)
	private Employe employe;

	

	public EmplPub(String date, Publication publication, Employe employe) {
		super();
		this.date = date;
		this.publication = publication;
		this.employe = employe;
	}

	public EmplPub() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
