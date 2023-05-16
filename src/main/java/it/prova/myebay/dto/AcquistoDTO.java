package it.prova.myebay.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;

public class AcquistoDTO {
	private Long id;
	private String descrizione;
	private LocalDate data;
	private Double prezzo;
	private UtenteDTO utente;
	
	public AcquistoDTO() {
		
	}
	
	public AcquistoDTO(Long id, String descrizione, LocalDate data, Double prezzo, UtenteDTO utente) {
		this.id=id;
		this.descrizione=descrizione;
		this.data=data;
		this.prezzo=prezzo;
		this.utente=utente;
	}
	
	public AcquistoDTO(String descrizione, LocalDate data, Double prezzo) {
		this.descrizione=descrizione;
		this.data=data;
		this.prezzo=prezzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	
	public Acquisto buildAcquistoModel() {
			
			Utente utenteModel = this.utente != null ? this.utente.buildUtenteModel(true) : null;
			Acquisto result = new Acquisto(this.id, this.descrizione, this.data, this.prezzo, utenteModel);
			
			return result;
		}
	
	
	// niente password...
		public static AcquistoDTO buildAcquistoDTOFromModel(Acquisto acquistoModel) {
			AcquistoDTO result = new AcquistoDTO(acquistoModel.getId(), acquistoModel.getDescrizione(), acquistoModel.getData(),
					acquistoModel.getPrezzo(), 	UtenteDTO.buildUtenteDTOFromModel(acquistoModel.getUtenteAcquirente(), false));

			return result;
		}

		public static List<AcquistoDTO> createAcquistoDTOListFromModelList(List<Acquisto> modelListInput) {
			return modelListInput.stream().map(acquistoEntity -> {
				return AcquistoDTO.buildAcquistoDTOFromModel(acquistoEntity);
			}).collect(Collectors.toList());
		}

}
