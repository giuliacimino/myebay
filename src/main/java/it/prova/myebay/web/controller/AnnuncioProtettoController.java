package it.prova.myebay.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.exception.AnnuncioChiusoException;
import it.prova.myebay.exception.UtenteNonTrovatoException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;

@Controller
@RequestMapping(value = "/utente/annuncioprotetto")
public class AnnuncioProtettoController {

	@Autowired
	AnnuncioService annuncioService;

	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("categorie_totali_attr",
				CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAllElements()));
		model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		return "utente/annuncioprotetto/insert";
	}

	@PostMapping("/save")
	public String save(@Validated @ModelAttribute("insert_annuncio_attr") AnnuncioDTO annuncioDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			model.addAttribute("categorie_totali_attr",
					CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAllElements()));
			return "utente/annuncioprotetto/insert";
		}
		try {
			annuncioService.inserisciNuovo(annuncioDTO.buildAnnuncioModel(true, true));
			redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		} catch (UtenteNonTrovatoException e) {
			redirectAttrs.addFlashAttribute("errorMessage", "Attenzione! Utente non loggato.");
			return "utente/annuncioprotetto/list";
		}
		return "redirect:/utente/annuncioprotetto/annunciutente";
	}

	@GetMapping("/annunciutente")
	public String listAllAcquistiUtente(Model model) {
		List<Annuncio> annunciUtente= annuncioService.cercaPerUtente_Username();
		List<AnnuncioDTO> annunciUtenteDTO= AnnuncioDTO.createAnnuncioDTOListFromModelList(annunciUtente, false);
		model.addAttribute("annuncioUtente_list_attr", annunciUtenteDTO);
		return "utente/annuncioprotetto/listutente";
	}
	
	@GetMapping("edit/{idAnnuncio}")
	public String editAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		Annuncio annuncioModel = annuncioService.caricaAnnuncioConCategorie(idAnnuncio);
		AnnuncioDTO annuncioDTO = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioModel, true);
		model.addAttribute("edit_annuncio_attr", annuncioDTO);
		model.addAttribute("categorie_totali_attr",
				CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAllElements()));
		return "utente/annuncioprotetto/edit";
	}

	@PostMapping("/edit")
	public String edit(@Valid @ModelAttribute("edit_annuncio_attr") AnnuncioDTO annuncioDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			model.addAttribute("categorie_totali_attr",
					CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAllElements()));
			return "utente/annuncioprotetto/edit";
		}
		try {
			annuncioService.aggiorna(annuncioDTO.buildAnnuncioModel(true, true));
			redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		} catch (AnnuncioChiusoException e) {
			redirectAttrs.addFlashAttribute("errorMessage",
					"Attenzione! L'annuncio che stai cercando di modificare è già chiuso.");
			return "redirect:/annuncio";
		} catch (UtenteNonTrovatoException e) {
			redirectAttrs.addFlashAttribute("errorMessage", "Attenzione! Utente non loggato.");
			return "redirect:/utente/annuncioprotetto";
		}

		return "redirect:/utente/annuncioprotetto/annunciutente";
	}
	
	@GetMapping("delete/{idAnnuncio}")
	public String deleteAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		Annuncio annuncioModel = annuncioService.caricaAnnuncioConCategorie(idAnnuncio);
		AnnuncioDTO annuncioDTO = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioModel, true);
		model.addAttribute("delete_annuncio_attr", annuncioDTO);
		model.addAttribute("categorie_totali_attr", annuncioModel.getCategorie());
		return "utente/annuncioprotetto/delete";
	}

	@PostMapping("/delete")
	public String delete(Long idAnnuncio, RedirectAttributes redirectAttrs) {
		try {
			annuncioService.rimuovi(idAnnuncio);
			redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		} catch (AnnuncioChiusoException e) {
			redirectAttrs.addFlashAttribute("errorMessage",
					"Attenzione! L'annuncio che stai cercando di eliminare è già chiuso.");
			return "redirect:/utente/annuncioprotetto";
		} catch (UtenteNonTrovatoException e) {
			redirectAttrs.addFlashAttribute("errorMessage", "Attenzione! Utente non loggato.");
			return "redirect:/utente/annuncioprotetto";

		} catch (RuntimeException er) {
			redirectAttrs.addFlashAttribute("errorMessage", "Attenzione! Non puoi eliminare un annuncio non tuo.");
			return "redirect:/utente/annuncioprotetto";
		}
		return "redirect:/utente/annuncioprotetto/annunciutente";

	}
	@GetMapping("/show/{idAnnuncio}")
	public String show(@PathVariable(required = true) Long idAnnuncio, Model model) {
		Annuncio annuncioModel = annuncioService.caricaAnnuncioConCategorie(idAnnuncio);
		AnnuncioDTO annuncioDTO = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioModel, true);
		model.addAttribute("show_annuncio_attr", annuncioDTO);
		model.addAttribute("categorie_totali_attr", annuncioModel.getCategorie());
		return "utente/annuncioprotetto/show";
	}
		

	

}
