package it.prova.myebay.repository.annuncio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioRepository extends CrudRepository<Annuncio, Long>, CustomAnnuncioRepository {
	@Query(value = "select a from Annuncio a left join a.categorie where a.id=?1")
	public Optional<Annuncio> findByIdConCategorie(Long id);
	
	@Query(value = "select a from Annuncio a left join a.utenteInserimento u where u.username=?1")
	public List<Annuncio> findByUtente_Username(String username);
	

}
