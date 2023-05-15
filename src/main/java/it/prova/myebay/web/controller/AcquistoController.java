package it.prova.myebay.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.model.Acquisto;
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
				AcquistoDTO.createAcquistoDTOListFromModelList(acquistoService.listAllElements()));
		mv.setViewName("acquisto/list");
		return mv;
	}

	@GetMapping("/acquistiutente")
	public String listAllAcquistiUtente(Model model) {
		List<Acquisto> acquisti= acquistoService.cercaPerUtente_Username();
		List<AcquistoDTO> acquistiDTO= AcquistoDTO.createAcquistoDTOListFromModelList(acquisti);
		model.addAttribute("acquisto_list_attr", acquistiDTO);
		return "/acquisto/list";
	}
	
	@PostMapping("/compra")
	public String compra(Long idAnnuncio, ModelMap model, RedirectAttributes redirectAttrs) {


		if (!utenteService.isAutenticato())
			return "login";

		acquistoService.inserisciNuovoAcquisto(idAnnuncio);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/acquisto";
	}

}
