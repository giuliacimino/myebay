package it.prova.myebay.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {
	
	@Autowired
	AnnuncioService annuncioService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		List<Annuncio> annunci = annuncioService.listAllElements();
		mv.addObject("annuncio_list_attr", AnnuncioDTO.createAnnuncioDTOListFromModelList(annunci, false));
		mv.setViewName("annuncio/list");
		return mv;
	}
	
	@GetMapping("/search")
	public String searchAnnuncio(Model model) {
		model.addAttribute("categorie_totali_attr",
				CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAllElements()));
		model.addAttribute("search_annuncio_attr", new AnnuncioDTO());
		return "annuncio/search";
	}
	@PostMapping("/list")
	public String listAnnunci(AnnuncioDTO annuncioExample, ModelMap model) {
		model.addAttribute("annuncio_list_attr", AnnuncioDTO.createAnnuncioDTOListFromModelList(
				annuncioService.findByExample(annuncioExample.buildAnnuncioModel(true, true)), true));
		return "annuncio/list";
	}
	@GetMapping("/show/{idAnnuncio}")
	public String show(@PathVariable(required = true) Long idAnnuncio, Model model) {
		Annuncio annuncioModel = annuncioService.caricaAnnuncioConCategorie(idAnnuncio);
		AnnuncioDTO annuncioDTO = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioModel, true);
		model.addAttribute("show_annuncio_attr", annuncioDTO);
		model.addAttribute("categorie_totali_attr", annuncioModel.getCategorie());
		return "annuncio/show";
	}

	
	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("categorie_totali_attr",
				CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAllElements()));
		model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		return "annuncio/insert";
	}

	@PostMapping("/save")
	public String save(@Validated @ModelAttribute("insert_annuncio_attr") AnnuncioDTO annuncioDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {
		
		if (result.hasErrors()) {
			model.addAttribute("categorie_totali_attr",CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAllElements()));
			return "annuncio/insert";
		}
		try {
			annuncioService.inserisciNuovo(annuncioDTO.buildAnnuncioModel(true, true));
			redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		} catch (RuntimeException e) {
			redirectAttrs.addFlashAttribute("errorMessage", "Attenzione! Utente non loggato.");
			return "public/annuncio/list";
		}
		return "annuncio/list";
	}
	
	


}