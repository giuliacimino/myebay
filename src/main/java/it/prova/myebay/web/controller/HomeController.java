package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

@Controller
public class HomeController {
	
	@Autowired
	private UtenteService utenteService;

	@Autowired
	CategoriaService categoriaService;
	


	@RequestMapping(value = { "/home", "" })
	public String home(Model model) {

		if (utenteService.isAutenticato()) {
			return "utente/index";
		} else

		model.addAttribute("categorie_totali_attr",
					CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAllElements()));
		model.addAttribute("search_annuncio_attr", new AnnuncioDTO());
		return "annuncio/search";
	
	}
}
