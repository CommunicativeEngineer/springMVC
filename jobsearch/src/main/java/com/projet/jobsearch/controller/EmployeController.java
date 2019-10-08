package com.projet.jobsearch.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projet.jobsearch.entities.Administrateur;
import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.entities.EmplPub;
import com.projet.jobsearch.entities.Employe;
import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Publication;
import com.projet.jobsearch.entities.Utilisateur;
import com.projet.jobsearch.metiers.ITraitementMetierAdministrateur;
import com.projet.jobsearch.metiers.ITraitementMetierCompetence;
import com.projet.jobsearch.metiers.ITraitementMetierEmplPub;
import com.projet.jobsearch.metiers.ITraitementMetierPublication;
import com.projet.jobsearch.metiers.ITraitementMetierUtilisateur;
import com.projet.jobsearch.repository.AdministrateurRepository;

@Controller
public class EmployeController {

	@Autowired
	private ITraitementMetierUtilisateur traitementMetierUtilisateur;
	@Autowired
	private ITraitementMetierCompetence traitementMetierCompetence;
	@Autowired
	private ITraitementMetierPublication traitementMetierPublication;
	@Autowired
	private ITraitementMetierEmplPub traitementMetierEmplPub;
	
	private String test = "accueil";
	private Long id_emp;
	private int i = 0;
	private Long i_pub;

	@RequestMapping(path = "employeAcc", method = RequestMethod.GET)
	public String getAcc(Model model, Long id,HttpSession session) {
		if(session.getAttribute("employe")!=null) {
		test = "accueil";
		if (id != null)
			id_emp = id;
		ArrayList<Publication> pub = traitementMetierPublication.listLastPub();
		model.addAttribute("pub", pub);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		return "accueilEmploye";
		}
		return "index"; 
	}

	@RequestMapping(path = "employeProf", method = RequestMethod.GET)
	public String getPro(Model model, Long id,HttpSession session) {
		if(session.getAttribute("employe")!=null) {
	
		test = "profil";
		if (id != null)
			id_emp = id;
		ArrayList<EmplPub> pub = traitementMetierEmplPub.listPubs(id_emp);
		model.addAttribute("pub", pub);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		return "accueilEmploye";
		}
		return "index"; 
	}
	
	@RequestMapping(path = "postuler", method = RequestMethod.GET)
	public String getPro(Model model, Long id, Long id_pub,HttpSession session) {
		if(session.getAttribute("employe")!=null) {
	 
		Date date = new Date();
		SimpleDateFormat tgl = new SimpleDateFormat("EEEE-dd-MMM-yyyy");
		SimpleDateFormat jam = new SimpleDateFormat("HH:mm");
		String format = tgl.format(date) + " " + jam.format(date);
		EmplPub ep=new EmplPub(format,traitementMetierPublication.getPub(id_pub),traitementMetierUtilisateur.getEmploye(id));
		traitementMetierEmplPub.saveProfilEmpl(ep);
		model.addAttribute("id", id_emp);
		return "redirect:/employeAcc";
		}
		return "index"; 
	}
/*
	@RequestMapping(path = "employeForm", method = RequestMethod.GET)
	public String getForm(Model model, Long id) {
		test = "profil";
		model.addAttribute("test", test);
		model.addAttribute("id", id);
		model.addAttribute("var", 1);// var ->employeurForm
		return "accueilEmployeur";
	}*/

	

	@RequestMapping(path = "employeFiltrer", method = RequestMethod.GET)
	public String getFiltrer(Model model, Long id, String enregistrer,HttpSession session) {
		if(session.getAttribute("employe")!=null) {
		test = "profil";
		ArrayList<EmplPub> pub = traitementMetierEmplPub.listPubs2(enregistrer,id_emp);
		model.addAttribute("pub", pub);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		return "accueilEmploye";
		}
		return "index"; 
	}

	@RequestMapping(path = "employeFiltrerAll", method = RequestMethod.GET)
	public String getFiltrerAll(Model model, Long id, String enregistrer,HttpSession session) {
		if(session.getAttribute("employe")!=null) {
		test = "accueil";
		System.out.print("enregistrer->"+enregistrer);
		ArrayList<Publication> pub = traitementMetierPublication.listPubsAll(enregistrer);
		for(int i=0;i<pub.size();i++)
			System.out.print(pub.get(i).getTitre()+"/");
		model.addAttribute("pub", pub);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		return "accueilEmploye";
		}
		return "index"; 
	}

	@RequestMapping("/suppEmplPub")
	public String suppPub(Model model, Long id_pub, Long id,HttpSession session) {
		if(session.getAttribute("employe")!=null) {
		test = "profil";
		traitementMetierEmplPub.deletePub(id_pub);
		model.addAttribute("id", id_emp);
		model.addAttribute("test", test);
		return "redirect:/employeProf";
		}
		return "index"; 
	}

	@RequestMapping("/updateInfosEmploye")
	public String updateInfos(Model model, Long id,HttpSession session) {
		if(session.getAttribute("employe")!=null) {
		test = "profil";
		Utilisateur utl = traitementMetierUtilisateur.getEmploye(id);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		model.addAttribute("utl", utl);
		model.addAttribute("log", utl.getLogin());
		return "accueilEmploye";
		}
		return "index"; 
	}

	@RequestMapping("/updateEmploye")
	public String updateEmployeur(Model model, Long id,  Employe employe, String log, String c1, String c2, String c3,HttpSession session) {
		if(session.getAttribute("employe")!=null) {
		String[] tab = new String[3];
		tab[0] = c1;
		tab[1] = c2;
		tab[2] = c3;
		test = "profil";
		model.addAttribute("test", test);
		model.addAttribute("id", id);
		employe.setId(id);
		ArrayList<Competence> c = traitementMetierCompetence.returnComp(employe.getId());
		for (int i = 0; i < c.size(); i++) {
			Competence com = c.get(i);
			com.setNom(tab[i]);
			com.setEmploye(employe);
			c.set(i, com);
		}
		if (traitementMetierUtilisateur.saveEmploye(employe) || employe.getLogin().equals(log)) {
			String env = employe.getNom() + " " + employe.getPrenom() + " est bien enregistre!";
			if (employe.getLogin().equals(log))
				traitementMetierUtilisateur.saveEmployeWithoutVer(employe);
			
			model.addAttribute("message", env);
			model.addAttribute("login", employe.getLogin());
			return "redirect:/employeProf?message=" + env + "&login=" + employe.getLogin();
		}
		model.addAttribute("message", employe.getLogin() + " existe deja!");
		return "redirect:/updateInfosEmploye";
		}
		return "index"; 
	}

	/*@RequestMapping("/accueilEmployeNew")
	public ModelAndView modPub(Model model, @RequestParam("message") String message,
			@RequestParam("login") String login, Long id,HttpSession session) {
		if(session.getAttribute("employe")!=null) {
		ModelAndView mav = new ModelAndView("accueilEmployeur");
		System.out.println("i'm in");
		mav.addObject("message", message);
		mav.addObject("login", login);
		test = "profil";
		model.addAttribute("test", test);
		return mav;
		}
		return null; 
	}*/



}
