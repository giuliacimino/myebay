package it.prova.myebay.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;

@Controller
@RequestMapping(value = "/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RuoloService ruoloService;
	
	@GetMapping
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("utente_list_attribute",
				UtenteDTO.createUtenteDTOListFromModelList(utenteService.listAll(), false));
		mv.setViewName("utente/list");
		return mv;
	}
	
	
	
	@GetMapping("/search")
	public String searchUtente(Model model) {
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAllElements()));
		model.addAttribute("search_utente_attr", new UtenteDTO());
		return "utente/search";
	}

	@PostMapping("/list")
	public String listUtenti(UtenteDTO utenteExample, ModelMap model) {
		model.addAttribute("utente_list_attribute", UtenteDTO.createUtenteDTOListFromModelList(
				utenteService.findByExample(utenteExample.buildUtenteModel(true)), true));
		return "utente/list";
	}
	
	@PostMapping("/cambiaStato")
	public String cambiaStato(@RequestParam(name = "idUtenteForChangingStato", required = true) Long idUtente) {
		utenteService.changeUserAbilitation(idUtente);
		return "redirect:/utente";
	}

	
	
	
}