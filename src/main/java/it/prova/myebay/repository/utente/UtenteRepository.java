package it.prova.myebay.repository.utente;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
	@EntityGraph(attributePaths = { "ruoli" })
	Optional<Utente> findByUsername(String username);
	
	@Query("from Utente u left join fetch u.ruoli where u.id = ?1")
	Optional<Utente> findByIdConRuoli(Long id);
	
	@EntityGraph(attributePaths = { "ruoli" })
	Utente findByUsernameAndPasswordAndStato(String username,String password, StatoUtente stato);
	
	Optional<Utente> findById (Long id);
	
	
	
	

}
