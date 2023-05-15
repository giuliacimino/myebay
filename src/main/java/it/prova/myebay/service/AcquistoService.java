package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Utente;


public interface AcquistoService {
	public List<Acquisto> listAllElements();

	public Acquisto caricaSingoloElemento(Long id);


	public void aggiorna(Acquisto acquistoInstance);

	public void inserisciNuovo(Acquisto acquistoInstance);

	public void rimuovi(Long idToDelete);

	public List<Acquisto> findByExample(Acquisto example);
	
	public List<Acquisto> cercaPerUtente_Username ();
	
	public Acquisto inserisciNuovoAcquisto(Long idAnnuncio);

}
