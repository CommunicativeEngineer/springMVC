/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.jobsearch.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "administrateur")
public class Administrateur  {

  
    @Id
    @GeneratedValue
    @Column(name = "id") 
    private Long id;
    @Column(name = "login")
    @NotEmpty(message = "veuillez saisir un login")
    private String login;
    @Column(name = "password")
    @NotEmpty(message = "veuillez saisir un mot de passe")
    private String password;
    @Column(name = "priorite") 
    @Min(value = 0 , message = "priorité égale à 0 ou à 1")
    @Max(value = 1, message = "priorité égale à 0 ou à 1")
    private int priorite;
    
    
    
    
    public Administrateur(String login, String password, int priorite) {
		super();
		this.login = login;
		this.password = password;
		this.priorite = priorite;
	}
    
    


	public Administrateur() {
		super();
	}




	public Long getId() {
        return id;
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

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }
    
    
}
