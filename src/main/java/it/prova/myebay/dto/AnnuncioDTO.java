package it.prova.myebay.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

public class AnnuncioDTO {
	
	private Long id;
	
	@NotBlank(message = "{testoannuncio.notblank}")
	private String testoAnnuncio;
	@NotNull(message = "{prezzo.notNull}")
	private Double prezzo;
	
	private LocalDate data;
	
	
	private boolean aperto;
	
	
	private Utente utenteInserimento;
	
	private Long[] categorieIds;
	
public AnnuncioDTO() {
		
	}
	
	public AnnuncioDTO(String testoAnnuncio, Double prezzo, LocalDate data, boolean aperto, Utente utenteInserimento) {
		this.testoAnnuncio=testoAnnuncio;
		this.prezzo=prezzo;
		this.data=data;
		this.aperto=aperto;
		this.utenteInserimento=utenteInserimento;
	}
	

	public AnnuncioDTO(Long id, String testoAnnuncio, Double prezzo, LocalDate data, boolean aperto, Utente utenteInserimento) {
		this.testoAnnuncio=testoAnnuncio;
		this.prezzo=prezzo;
		this.data=data;
		this.aperto=aperto;
		this.utenteInserimento=utenteInserimento;
	}
	
	public AnnuncioDTO(String testoAnnuncio, Double prezzo, LocalDate data, boolean aperto) {
		this.testoAnnuncio=testoAnnuncio;
		this.prezzo=prezzo;
		this.data=data;
		this.aperto=aperto;
	}
	
	public Annuncio buildAnnuncioModel() {
		Annuncio result = new Annuncio(this.id, this.testoAnnuncio, this.prezzo, this.data, this.aperto);
		return result;
		}

	// niente password...
	public static AnnuncioDTO buildAnnuncioDTOFromModel(Annuncio annuncioModel, boolean includesCategorie) {
		AnnuncioDTO result = new AnnuncioDTO(annuncioModel.getId(), annuncioModel.getTestoAnnuncio(), annuncioModel.getPrezzo(),
				annuncioModel.getData(), annuncioModel.isAperto(), annuncioModel.getUtenteInserimento());

		if (includesCategorie && !annuncioModel.getCategorie().isEmpty())
			result.categorieIds = annuncioModel.getCategorie().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}

	public static List<AnnuncioDTO> createAnnuncioDTOListFromModelList(List<Annuncio> modelListInput, boolean includesCategorie) {
		return modelListInput.stream().map(annuncioEntity -> {
			return AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioEntity, includesCategorie);
		}).collect(Collectors.toList());
	}
	
	



}
