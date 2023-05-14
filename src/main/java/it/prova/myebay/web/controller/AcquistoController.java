package it.prova.myebay.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.AcquistoService;
import it.prova.myebay.service.UtenteService;

@Controller
@RequestMapping(value = "/acquisto")
public class AcquistoController {
	
	@Autowired
	AcquistoService acquistoService;
	
	@Autowired
	UtenteService utenteService;
	
	
	@GetMapping
	public ModelAndView listAllAcquisti() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("acquisto_list_attr",
				AcquistoDTO.createAcquistoDTOListFromModelList(acquistoService.cercaPerUtente_Username()));
		mv.setViewName("acquisto/list");
		return mv;
	}
	
	@GetMapping("/acquistiutente")
	public String acquistiPerUtente (ModelMap model) {
		acquistoService.cercaPerUtente_Username();
		return "acquisto/list";
	}

}
