package it.prova.myebay.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.exception.CreditoInsufficienteException;
import it.prova.myebay.exception.UtenteNonTrovatoException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService {

	@Autowired
	AcquistoRepository acquistoRepository;

	@Autowired
	UtenteService utenteService;

	@Autowired
	AnnuncioService annuncioService;

	@Transactional(readOnly = true)
	public List<Acquisto> listAllElements() {
		return (List<Acquisto>) acquistoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Acquisto caricaSingoloElemento(Long id) {
		return acquistoRepository.findById(id).orElse(null);

	}

	@Override
	@Transactional
	public void aggiorna(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);

	}

	@Override
	@Transactional
	public void inserisciNuovo(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		acquistoRepository.deleteById(idToDelete);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Acquisto> findByExample(Acquisto example) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Acquisto> cercaPerUtente_Username() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return acquistoRepository.findAllByUtente_Username(username);
	}

	@Override
	@Transactional
	public Acquisto inserisciNuovoAcquisto(Long idAnnuncio) {
		Acquisto nuovoAcquisto = new Acquisto();

		Annuncio annunciodaAcquistare = annuncioService.caricaSingoloElemento(idAnnuncio);

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Utente utente = utenteService.findByUsername(username);

		if (utente.getId() == null) {
			throw new UtenteNonTrovatoException();
		}

		if (utente.getCreditoResiduo() < annunciodaAcquistare.getPrezzo()) {
			throw new CreditoInsufficienteException();
		}

		// settiamo i crediti
		Double nuovoCreditoAcquirente = utente.getCreditoResiduo() - annunciodaAcquistare.getPrezzo();

		utente.setCreditoResiduo(nuovoCreditoAcquirente);

		Double nuovoCreditoVenditore = annunciodaAcquistare.getUtenteInserimento().getCreditoResiduo()
				+ annunciodaAcquistare.getPrezzo();

		annunciodaAcquistare.getUtenteInserimento().setCreditoResiduo(nuovoCreditoVenditore);

		// chiudiamo l'annuncio

		annuncioService.caricaSingoloElemento(idAnnuncio).setAperto(false);

		// settiamo i campi del nuovo acquisto
		nuovoAcquisto.setData(LocalDate.now());
		nuovoAcquisto.setDescrizione(annuncioService.caricaSingoloElemento(idAnnuncio).getTestoAnnuncio());
		nuovoAcquisto.setPrezzo(annunciodaAcquistare.getPrezzo());
		nuovoAcquisto.setUtenteAcquirente(utente);

		return acquistoRepository.save(nuovoAcquisto);

	}

}
