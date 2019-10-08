/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.jobsearch.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@DiscriminatorColumn(name="TYPE_USER",
discriminatorType=DiscriminatorType.STRING,length=4)
@Table(name = "utilisateur")
public abstract class Utilisateur {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected Long id;
    @Column(name = "login") 
    private String login;
    @Column(name = "password") 
    private String password;
    @Column(name = "nom") 
    private String nom;
    @Column(name = "prenom") 
    private String prenom;
    @Column(name = "telephone")
    @Length(min = 8 )
    private String telephone;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "pays") 
    private String pays;
    @Column(name = "adresse") 
    private String adresse;


    
    
    
    public Utilisateur(String login, String password, String nom, String prenom, String telephone, String email,
			String pays, String adresse) {
		super();
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.pays = pays;
		this.adresse = adresse;
	}


    
    
	public Utilisateur() {
		super();
	}




	public Long getId() {
        return id;
    }

	public void setId(Long id)
	{
		this.id=id;
	}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String motDePasse) {
        this.password = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    
}
