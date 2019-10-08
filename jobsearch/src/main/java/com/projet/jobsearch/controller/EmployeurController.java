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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projet.jobsearch.entities.Administrateur;
import com.projet.jobsearch.entities.Competence;
import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Publication;
import com.projet.jobsearch.entities.Utilisateur;
import com.projet.jobsearch.metiers.ITraitementMetierAdministrateur;
import com.projet.jobsearch.metiers.ITraitementMetierCompetence;
import com.projet.jobsearch.metiers.ITraitementMetierPublication;
import com.projet.jobsearch.metiers.ITraitementMetierUtilisateur;
import com.projet.jobsearch.metiers.TraitementEmployeur;
import com.projet.jobsearch.repository.AdministrateurRepository;

@Controller
public class EmployeurController {

	@Autowired
	private ITraitementMetierUtilisateur traitementMetierUtilisateur;
	@Autowired
	private ITraitementMetierCompetence traitementMetierCompetence;
	@Autowired
	private ITraitementMetierPublication traitementMetierPublication;
	@Autowired
	private ITraitementMetierAdministrateur traitementMetierAdministrateur;
	
	private String test = "accueil";
	private Long id_emp;
	private int i = 0;
	private Long i_pub;

	@RequestMapping(path = "employeurAcc", method = RequestMethod.GET)
	public String getAcc(Model model, Long id,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "accueil";
		if (id != null)
			id_emp = id;
		ArrayList<Publication> pub = traitementMetierPublication.listLastPub();
		model.addAttribute("pub", pub);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		return "accueilEmployeur";
		}
		return "index"; 
	}

	@RequestMapping(path = "employeurProf", method = RequestMethod.GET)
	public String getPro(Model model, Long id,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
	 
		test = "profil";
		if (id != null)
			id_emp = id;
		ArrayList<Publication> pub = traitementMetierPublication.listLastPubEmpr(id_emp);
		model.addAttribute("pub", pub);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		return "accueilEmployeur";
		}
		return "index"; 
	}

	@RequestMapping(path = "employeurForm", method = RequestMethod.GET)
	public String getForm(Model model, Long id,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "profil";
		model.addAttribute("test", test);
		model.addAttribute("id", id);
		model.addAttribute("var", 1);// var ->employeurForm
		return "accueilEmployeur";
		}
		return "index"; 
	}

	@RequestMapping(path = "rempForm", method = RequestMethod.POST)
	public String getRempForm(Model model,  Publication publication, String c1, String c2, String c3, Long id,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "profil";
		Publication tampon;
		Date date = new Date();
		SimpleDateFormat tgl = new SimpleDateFormat("EEEE-dd-MMM-yyyy");
		SimpleDateFormat jam = new SimpleDateFormat("HH:mm");
		String format = tgl.format(date) + " " + jam.format(date);
		publication.setDate(format);
		publication.setEmployeur(traitementMetierUtilisateur.getEmployeur(id_emp));
		traitementMetierPublication.savePub(publication);
		Collection<Competence> c = new ArrayList<Competence>();
		Competence com1 = new Competence(" ");
		Competence com2 = new Competence(" ");
		Competence com3 = new Competence(" ");
		if (!c1.equals("")) {
			com1.setNom(c1);
		}
		com1.setPublication(publication);
		c.add(com1);
		if (!c2.equals("")) {
			com2.setNom(c2);
		}
		com2.setPublication(publication);
		c.add(com2);
		if (!c3.equals("")) {
			com3.setNom(c3);
		}
		com3.setPublication(publication);
		c.add(com3);
		publication.setCompetences(c);
		traitementMetierCompetence.saveCompetences((ArrayList<Competence>) c);
		model.addAttribute("test", test);
		return "redirect:/employeurProf";
		}
		return "index"; 
	}

	@RequestMapping(path = "updateForm", method = RequestMethod.POST)
	public String getUpdateForm(Model model, Publication publication, String c1, String c2, String c3, Long id_pub,
			Long id,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "profil";
		String[] tab = new String[3];
		tab[0] = c1;
		tab[1] = c2;
		tab[2] = c3;
		publication.setId(id_pub);
		Date date = new Date();
		SimpleDateFormat tgl = new SimpleDateFormat("EEEE-dd-MMM-yyyy");
		SimpleDateFormat jam = new SimpleDateFormat("HH:mm");
		String format = tgl.format(date) + " " + jam.format(date);
		publication.setDate(format);
		publication.setEmployeur(traitementMetierUtilisateur.getEmployeur(id_emp));
		traitementMetierPublication.savePub(publication);
		ArrayList<Competence> c = traitementMetierCompetence.returnComp(publication.getId());
		for (int i = 0; i < c.size(); i++) {
			Competence com = c.get(i);
			com.setNom(tab[i]);
			com.setPublication(publication);
			c.set(i, com);

		}
		publication.setCompetences(c);
		traitementMetierCompetence.saveCompetences((ArrayList<Competence>) c);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		return "redirect:/employeurProf";
		}
		return "index"; 
	}

	@RequestMapping(path = "employeurFiltrer", method = RequestMethod.GET)
	public String getFiltrer(Model model, Long id, String enregistrer,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "profil";
		ArrayList<Publication> pub = traitementMetierPublication.listPubs(enregistrer,id_emp);
		model.addAttribute("pub", pub);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		return "accueilEmployeur";
		}
		return "index"; 
	}

	@RequestMapping(path = "employeurFiltrerAll", method = RequestMethod.GET)
	public String getFiltrerAll(Model model, Long id, String enregistrer,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "accueil";
		ArrayList<Publication> pub = traitementMetierPublication.listPubsAll(enregistrer);
		model.addAttribute("pub", pub);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		return "accueilEmployeur";
		}
		return "index"; 
	}

	@RequestMapping("/suppPub")
	public String suppPub(Model model, Long id_pub, Long id,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "profil";
		traitementMetierPublication.deletePub(id_pub);
		traitementMetierCompetence.deleteComps(id_pub);
		model.addAttribute("id", id_emp);
		model.addAttribute("test", test);
		return "redirect:/employeurProf";
		}
		return "index"; 
	}

	@RequestMapping("/updateInfos")
	public String updateInfos(Model model, Long id,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "profil";
		Utilisateur utl = traitementMetierUtilisateur.getEmployeur(id);
		model.addAttribute("test", test);
		model.addAttribute("id", id_emp);
		model.addAttribute("utl", utl);
		model.addAttribute("log", utl.getLogin());
		return "accueilEmployeur";
		}
		return "index"; 
	}

	@RequestMapping("/updateEmployeur")
	public String updateEmployeur(Model model, Long id, Employeur employeur, String log,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "profil";
		model.addAttribute("test", test);
		model.addAttribute("id", id);
		employeur.setId(id);
		if (traitementMetierUtilisateur.saveEmployeur(employeur) || employeur.getLogin().equals(log)) {
			String env = employeur.getNom() + " " + employeur.getPrenom() + " est bien enregistre!";
			if (employeur.getLogin().equals(log))
				traitementMetierUtilisateur.saveEmployeurWithoutVer(employeur);
			model.addAttribute("message", env);
			model.addAttribute("login", employeur.getLogin());
			return "redirect:/employeurProf?message=" + env + "&login=" + employeur.getLogin();
		}
		model.addAttribute("message", employeur.getLogin() + " existe deja!");
		return "redirect:/updateInfos";
		}
		return "index"; 
	}

	/*@RequestMapping("/accueilEmployeurNew")
	public ModelAndView modPub(Model model, @RequestParam("message") String message,
			@RequestParam("login") String login, Long id) {
		ModelAndView mav = new ModelAndView("accueilEmployeur");
		System.out.println("i'm in");
		mav.addObject("message", message);
		mav.addObject("login", login);
		test = "profil";
		model.addAttribute("test", test);
		return mav;
	} */

	@RequestMapping("/modPub")
	public String modPub(Model model, Long id_pub, Long id,HttpSession session) {
		if(session.getAttribute("employeur")!=null) {
		test = "profil";
		i = 1;
		i_pub = id_pub;
		model.addAttribute("test", test);
		Publication tampon = traitementMetierPublication.getPub(i_pub);
		model.addAttribute("id", id);
		model.addAttribute("plein", tampon);
		return "accueilEmployeur";
		}
		return "index"; 
	}

	

}
