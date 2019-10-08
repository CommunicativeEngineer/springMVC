package com.projet.jobsearch.controller;



import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.projet.jobsearch.entities.Administrateur;
import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.entities.Employe;
import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Utilisateur;
import com.projet.jobsearch.metiers.ITraitementMetierAdministrateur;
import com.projet.jobsearch.metiers.ITraitementMetierCompetence;
import com.projet.jobsearch.metiers.ITraitementMetierPublication;
import com.projet.jobsearch.metiers.ITraitementMetierUtilisateur;
import com.projet.jobsearch.repository.AdministrateurRepository;
import com.projet.jobsearch.repository.CompetenceRepository;
import com.projet.jobsearch.repository.PublicationRepository;
import com.projet.jobsearch.repository.UtilisateurRepository;



@Controller
public class JobControler {

	@Autowired
	private ITraitementMetierUtilisateur traitementMetierUtilisateur;
	@Autowired
	private ITraitementMetierCompetence traitementMetierCompetence;
	@Autowired
	private ITraitementMetierPublication traitementMetierPublication;
	@Autowired
	private ITraitementMetierAdministrateur traitementMetierAdministrateur;
	
	private String pageAct="";

	
	@RequestMapping("/")
	public String getindex(Model model, HttpSession session)
	{
		if(session.getAttribute("user")==null)
		return "index";
		return "redirect:/auth";
	}
	
	@RequestMapping("/inscription")
	public String getInscription(Model model, HttpSession session)
	{if(session.getAttribute("user")==null)
		return "inscription";
	return "redirect:/auth";
	}
	
	@RequestMapping("/login")
	public String getLogin(Model model, HttpSession session)
	{if(session.getAttribute("user")==null)
		return "login";
	return "redirect:/auth";
	}
	
	 
	
	
	//security
	@RequestMapping("/auth")
	public String getAuth(Model model,String login,String password, HttpSession session)
	{int verif=0;
	String test="accueil";
	Utilisateur uu=traitementMetierUtilisateur.authUser(login, password);
	if(session.getAttribute("user")==null){
		session.setAttribute("user", login);
		if(traitementMetierUtilisateur.authLogin(login, password).equals("user"))
		{
		
		Utilisateur u=traitementMetierUtilisateur.authUser(login, password);
		if(u.getClass().getSimpleName().equals("Employe")) 
		{session.setAttribute("employe", u);
		pageAct="redirect:/employeAcc?id="+uu.getId();}
		else if(u.getClass().getSimpleName().equals("Employeur")) 
		{session.setAttribute("employeur", u);
		pageAct="redirect:/employeurAcc?id="+u.getId();}
		model.addAttribute("empl", uu);
		model.addAttribute("nom", uu.getPrenom()+" "+uu.getNom());
		}
	else if(traitementMetierUtilisateur.authLogin(login, password).equals("admin"))
	{	
		Administrateur u=traitementMetierAdministrateur.authAdmin(login, password);
		session.setAttribute("admin", u); 
		pageAct="redirect:/accueilAdministrateur";
	}
	else{
		String str="votre login ou  password sont incorrects!";
		verif=1;
		model.addAttribute("verif", verif);
	model.addAttribute("erreur", str);
	session.invalidate();
	pageAct="redirect:/login?verif="+verif+"&message="+str;
	}
		}
	model.addAttribute("test", "accueil");
	return pageAct;
	
	}
	
	
	@RequestMapping("/inscriptionEmploye")
	public String insEmpe(Model model, HttpSession session)
	{
		if(session.getAttribute("user")==null)
		return "inscriptionEmploye";
		return "redirect:/auth";
	}
	
	@RequestMapping("/inscriptionEmployeur")
	public String insEmpr(Model model, HttpSession session)
	{if(session.getAttribute("user")==null)
		return "inscriptionEmployeur";
	return "redirect:/auth";
	}
	

	@RequestMapping(path="/saveEmploye",method = RequestMethod.POST)
	public String saveEmpe(Model model, Employe employe,String c1,String c2,String c3, HttpSession session)
	{if(session.getAttribute("user")==null){
		if(traitementMetierUtilisateur.saveEmploye(employe))
		{System.out.println(employe.getDomaine());
		
		Collection<Competence> c = new ArrayList<Competence>();
		Competence com1 = new Competence(" ");
		Competence com2 = new Competence(" ");
		Competence com3 = new Competence(" ");
		if (!c1.equals("")) {
			com1.setNom(c1);
		}
		com1.setEmploye(employe);
		c.add(com1);
		if (!c2.equals("")) {
			com2.setNom(c2);
		}
		com2.setEmploye(employe);
		c.add(com2);
		if (!c3.equals("")) {
			com3.setNom(c3);
		}
		com3.setEmploye(employe);
		c.add(com3);
		employe.setCompetences(c);
		traitementMetierCompetence.saveCompetences((ArrayList<Competence>) c);
		
		model.addAttribute("message", employe.getNom()+" "+employe.getPrenom()+" est bien enregistré!");
			model.addAttribute("login", employe.getLogin());
			return "redirect:/login";
		}
		model.addAttribute("message", employe.getLogin()+" existe déjà!");
		return "redirect:/inscriptionEmploye";}
	return "redirect:/auth";
	
	}
	

	@RequestMapping(path="/saveEmployeur",method = RequestMethod.POST)
	public String saveEmpr(Model model,  Employeur employeur, HttpSession session)
	{
		if(session.getAttribute("user")==null){
		if(traitementMetierUtilisateur.saveEmployeur(employeur))
	{String env=employeur.getNom()+" "+employeur.getPrenom()+" est bien enregistre!";
		model.addAttribute("message", env);
		model.addAttribute("login", employeur.getLogin());
		return "redirect:/login?message="+env+"&login="+employeur.getLogin();
	}
	model.addAttribute("message", employeur.getLogin()+" existe déjà!");
	return "redirect:/inscriptionEmployeur";}
		return "redirect:/auth";
	}
	
	
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session)
	{
		session.invalidate();
		return "index";
	}
	
	
}
