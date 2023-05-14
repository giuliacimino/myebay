package it.prova.myebay.service;

import java.util.Optional;

import it.prova.myebay.model.Utente;

public interface UtenteService {
	public Optional<Utente> findByUsername (String username);
	public void inserisciNuovo(Utente utenteInstance);
	public void changeUserAbilitation(Long utenteInstanceId);
	public Utente caricaSingoloUtente (Long utenteId);


}
