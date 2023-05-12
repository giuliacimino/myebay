package it.prova.myebay.dto;

import java.time.LocalDate;
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
import it.prova.myebay.model.Utente;

public class AcquistoDTO {
	private Long id;
	private String descrizione;
	private LocalDate data;
	private Double prezzo;
	private Utente utenteAcquirente;
	
	public AcquistoDTO() {
		
	}
	
	public AcquistoDTO(Long id, String descrizione, LocalDate data, Double prezzo, Utente utenteAcquirente) {
		this.id=id;
		this.descrizione=descrizione;
		this.data=data;
		this.prezzo=prezzo;
		this.utenteAcquirente=utenteAcquirente;
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

	public Utente getUtenteAcquirente() {
		return utenteAcquirente;
	}

	public void setUtenteAcquirente(Utente utenteAcquirente) {
		this.utenteAcquirente = utenteAcquirente;
	}
	
	
	// niente password...
		public static AcquistoDTO buildAcquistoDTOFromModel(Acquisto acquistoModel) {
			AcquistoDTO result = new AcquistoDTO(acquistoModel.getId(), acquistoModel.getDescrizione(), acquistoModel.getData(),
					acquistoModel.getPrezzo(), acquistoModel.getUtenteAcquirente());

			return result;
		}

		public static List<AcquistoDTO> createAcquistoDTOListFromModelList(List<Acquisto> modelListInput) {
			return modelListInput.stream().map(acquistoEntity -> {
				return AcquistoDTO.buildAcquistoDTOFromModel(acquistoEntity);
			}).collect(Collectors.toList());
		}

}
