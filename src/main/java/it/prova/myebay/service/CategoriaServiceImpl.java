package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.repository.categoria.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> listAllElements() {
		return (List<Categoria>) categoriaRepository.findAll();

	}

	@Override
	public Categoria caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Categoria ctegoriaInstance) {
		categoriaRepository.save(ctegoriaInstance);
		
	}

	@Override
	public void rimuovi(Long idToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categoria> findByExample(Categoria example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria findByDescrizione(String descrizione) {
		return categoriaRepository.findByDescrizione(descrizione);
	}
	
	
	
	
	

}
