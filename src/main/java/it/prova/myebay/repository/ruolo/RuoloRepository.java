package it.prova.myebay.repository.ruolo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long>{
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
	
	


}
