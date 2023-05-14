package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;

public interface CategoriaService {
	public List<Categoria> listAllElements();

	public Categoria caricaSingoloElemento(Long id);


	public void aggiorna(Categoria categoriaInstance);

	public void inserisciNuovo(Categoria ctegoriaInstance);

	public void rimuovi(Long idToDelete);

	public List<Categoria> findByExample(Categoria example);
	
	public Categoria findByDescrizione(String descrizione);


}
