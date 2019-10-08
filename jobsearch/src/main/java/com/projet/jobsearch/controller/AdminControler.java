package com.projet.jobsearch.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.jobsearch.entities.Administrateur;
import com.projet.jobsearch.entities.Employeur;
import com.projet.jobsearch.entities.Publication;
import com.projet.jobsearch.metiers.ITraitementMetierAdministrateur;
import com.projet.jobsearch.metiers.ITraitementMetierPublication;
import com.projet.jobsearch.metiers.ITraitementStatistiques;
import com.projet.jobsearch.metiers.ImplTraitementMetierAdministrateur;
import com.projet.jobsearch.metiers.ImplTraitementMetierPublication; 
import com.projet.jobsearch.metiers.TraitementEmployeur; 
import com.projet.jobsearch.metiers.TraitementStatistiques;
import com.projet.jobsearch.repository.AdministrateurRepository;
import com.projet.jobsearch.repository.CompetenceRepository;
import com.projet.jobsearch.repository.PublicationRepository;
import com.projet.jobsearch.repository.UtilisateurRepository;
import javax.validation.Valid;


@Controller 
public class AdminControler {
 
	@Autowired
	private ImplTraitementMetierAdministrateur traitementAdmin;
	@Autowired
	private ITraitementStatistiques traitementStatistiques;
	@Autowired
	private  TraitementEmployeur traitementEmployeur;
	@Autowired
	private ImplTraitementMetierPublication traitementPublication;
	@Autowired
	private ITraitementMetierPublication traitementMetierPublication;
	@Autowired
	private ITraitementMetierAdministrateur iTraitementMetierAdministrateur;

	 
	@RequestMapping("/accueilAdministrateur")
	public String index (ModelMap modelMap,HttpSession session)
	{	
		if(session.getAttribute("admin")!=null) {
		String test = "accueil";
System.out.println( traitementStatistiques.pourcentageEmploye());
	modelMap.put("test", test);
	modelMap.put("pourcentageEmploye", traitementStatistiques.pourcentageEmploye());
		modelMap.put("nbrEmploye", traitementStatistiques.countEmploye());
		modelMap.put("nbrEmployeur", traitementStatistiques.countEmployeur());
		modelMap.put("nbrPublication", traitementStatistiques.countPublication());
		modelMap.put("nbrPublicationActive", traitementStatistiques.countPublicationActive());
		return "/accueilAdministrateur";
		}
		return "index"; 
	}
	
	 
/*	@RequestMapping("/error")
	public String errorRedirect ()
	{		
		return "erreur"; 
	}*/
	
	@RequestMapping("/listAdmin")
	public String adminList (ModelMap modelMap,HttpSession session)

	{
		if(session.getAttribute("admin")!=null) {
		String test = "admin";
		Administrateur a=(Administrateur) session.getValue("admin"); 
		modelMap.put("test", test);
		modelMap.put("administrateurs", traitementAdmin.adminList());
		Administrateur administrateur = new Administrateur();
		modelMap.put("administrateur", administrateur);
		return "/accueilAdministrateur";
		}
		return "index"; 
		 
	}

	
	@RequestMapping(path = "/ajoutAdminForm", method = RequestMethod.GET)
	public String ajoutAdminForm(ModelMap modelMap,HttpSession session){
		if(session.getAttribute("admin")!=null) {
			Administrateur a = (Administrateur)session.getValue("admin");
			if(a.getPriorite()==0) {
	        Administrateur administrateur = new Administrateur();
	        modelMap.put("administrateur", administrateur);
	        modelMap.put("erreur3", "");
	        
	        return "/ajoutAdmin";}
			modelMap.put("erreur3", "cette action n'est pas permise avec une priorite 1");
			modelMap.put("administrateurs", traitementAdmin.adminList());
			modelMap.put("test", "admin");
			return "accueilAdministrateur"; 
		}
		return "index"; 
	    }
	
	@RequestMapping(path = "/adminAjout", method = RequestMethod.POST)
	public String adminAjout(ModelMap modelMap, @Valid Administrateur administrateur, BindingResult bindingResult ,HttpSession session) {
		if(session.getAttribute("admin")!=null) {	
			Administrateur a = (Administrateur)session.getValue("admin");
			if(a.getPriorite()==0) {
		if( iTraitementMetierAdministrateur.trouverParLogin(administrateur.getLogin()).isEmpty() 
				&& !administrateur.getPassword().isEmpty()) {
			 
			traitementAdmin.adminAjout(administrateur);
			modelMap.put("erreur1", "");
			modelMap.put("erreur2", "");
			modelMap.put("administrateurs", traitementAdmin.adminList());
			modelMap.put("test", "admin");
			return "accueilAdministrateur";
				
		}
		else 
		{
			if (bindingResult.hasErrors()) {
	            return "/ajoutAdmin";
	        }
			
			if (!iTraitementMetierAdministrateur.trouverParLogin(administrateur.getLogin()).isEmpty() ) {
			 
			modelMap.put("erreur1", "login deja utilise!");
				
			}
			/*if (administrateur.getPassword().isEmpty()) {
				modelMap.put("erreur2", "mot de passe vide!");	
			}*/
		
			
			
			return "/ajoutAdmin";
		
			 
		}
			}
		}
		return "index";
	}
	
	@RequestMapping(path = "/deleteAdmin", method = RequestMethod.GET)
    public String deleteAdmin(ModelMap modelMap, @RequestParam(name = "id") int id,HttpSession session){
		if(session.getAttribute("admin")!=null) {
			Administrateur a = (Administrateur)session.getValue("admin");
			if(a.getPriorite()==0) {
        traitementAdmin.deleteAdmin(id);
        if (id== a.getId()){
        return "redirect:/logout";	
        }
        return "/accueilAdministrateur";
		}
		 
			else
			{
				modelMap.put("erreur3", "vous ne pouvez pas supprimer!");
				modelMap.put("administrateurs", traitementAdmin.adminList());
				modelMap.put("test", "admin");
				return "accueilAdministrateur";
			}
		}
		return "index"; 
    }

	@RequestMapping(path = "/modifAdminForm", method = RequestMethod.GET)
	public String modifAdminForm(ModelMap modelMap, @RequestParam(name = "id") int id,HttpSession session) {
		if(session.getAttribute("admin")!=null) { 
			Administrateur a = (Administrateur)session.getValue("admin");
			if(a.getPriorite()==0) { 
		  modelMap.addAttribute("administrateur",traitementAdmin.adminInfo(id));
			 
		  return "/modifAdmin";
			}
			modelMap.put("erreur3", "vous ne pouvez pas modifier!");
			modelMap.put("administrateurs", traitementAdmin.adminList());
			modelMap.put("test", "admin");
			return "/accueilAdministrateur";
	}
	return "index"; 
	    }
  
	@RequestMapping(path = "/adminModif", method = RequestMethod.POST)
	public String adminModif(ModelMap modelMap,  Administrateur administrateur,HttpSession session) {
		if(session.getAttribute("admin")!=null) {	
			Administrateur a = (Administrateur)session.getValue("admin");
			if(a.getPriorite()==0) {
		if(  !administrateur.getPassword().isEmpty()) {
			 
			traitementAdmin.adminAjout(administrateur);
			modelMap.put("erreur1", "");
			modelMap.put("erreur2", "");
			modelMap.put("administrateurs", traitementAdmin.adminList());
			modelMap.put("test", "admin");
			return "accueilAdministrateur";
				
		}
		else 
		{
			 
			if (administrateur.getPassword().isEmpty()) {
				modelMap.put("erreur2", "mot de passe vide!");	
			}
		
			return "/modifAdmin";
		
			 
		}
			}
		}
		return "index";
	}
	
	@RequestMapping("/listEmployeur")
	public String emplyeurList (ModelMap modelMap,HttpSession session)

	{
		if(session.getAttribute("admin")!=null) {
		String test = "publieur";
		
		modelMap.put("test", test);
		
		modelMap.put("employeurs", traitementEmployeur.employeurList());
		
		return "/accueilAdministrateur";
	}
	return "index";
	}
	
	 
	
	@RequestMapping(path = "/deleteEmployeur", method = RequestMethod.GET)
    public String deleteEmployeur(ModelMap modelMap, @RequestParam(name = "id") int id,HttpSession session){
		if(session.getAttribute("admin")!=null) {
		traitementEmployeur.deleteEmployeur(id); 
         
String test = "publieur";
		
		modelMap.put("test", test);
		modelMap.put("employeurs", traitementEmployeur.employeurList());
		return "accueilAdministrateur";
	}
	return "index"; 
    }
	
  
	 
	@RequestMapping("/listPublication")
	public String publicationList (ModelMap modelMap,HttpSession session)

	{
		if(session.getAttribute("admin")!=null) {
		String test = "publication";
		Publication publication = new Publication();
		modelMap.put("publication", publication);
		modelMap.put("test", test);
		modelMap.put("publications", traitementPublication.publicationList());
		
		return "/accueilAdministrateur";
		}
		return "index"; 
	}

	
	 
	
	@RequestMapping(path = "/deletePublication", method = RequestMethod.GET)
    public String deletePublication(ModelMap modelMap, @RequestParam(name = "id") int id,HttpSession session){
		if(session.getAttribute("admin")!=null) {
        traitementPublication.deletePublication((long)id); 
		modelMap.put("publications", traitementPublication.publicationList());
		modelMap.put("test", "publication");
        return "accueilAdministrateur";
	}
	return "index"; 
    } 
	
	 
	
	@RequestMapping(path = "/recherchePublication", method = RequestMethod.POST)
	public String recherchePublication (ModelMap modelMap, Publication publication, String enregistrer,HttpSession session) {
		if(session.getAttribute("admin")!=null) {
		String test = "publication";
		 modelMap.put("test",test);
		ArrayList<Publication> pub = traitementMetierPublication.listPubsAll(enregistrer);
		 modelMap.addAttribute("publications",pub);
		return "/accueilAdministrateur";
		}
		return "index"; 
	}
}
