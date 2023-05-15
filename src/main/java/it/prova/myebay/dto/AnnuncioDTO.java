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

	public AnnuncioDTO(Long id, String testoAnnuncio, Double prezzo, LocalDate data, Utente utenteInserimento) {
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.utenteInserimento = utenteInserimento;
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Double prezzo, LocalDate data, boolean aperto,
			Utente utenteInserimento) {
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utenteInserimento = utenteInserimento;
	}

	public AnnuncioDTO(String testoAnnuncio, Double prezzo, LocalDate data, boolean aperto) {
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public boolean isAperto() {
		return aperto;
	}

	public void setAperto(boolean aperto) {
		this.aperto = aperto;
	}

	public Utente getUtenteInserimento() {
		return utenteInserimento;
	}

	public void setUtenteInserimento(Utente utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	public Long[] getCategorieIds() {
		return categorieIds;
	}

	public void setCategorieIds(Long[] categorieIds) {
		this.categorieIds = categorieIds;
	}

	public Annuncio buildAnnuncioModel(boolean aperto, boolean includesCategories) {
		Annuncio result = new Annuncio(this.id, this.testoAnnuncio, this.prezzo, this.data, this.utenteInserimento);
		if (includesCategories && categorieIds != null) {
			result.setCategorie(
					Arrays.asList(categorieIds).stream().map(id -> new Categoria(id)).collect(Collectors.toSet()));
		}
		if (aperto) {
			result.setAperto(true);
		}
		return result;
	}

	// niente password...
	public static AnnuncioDTO buildAnnuncioDTOFromModel(Annuncio annuncioModel, boolean includesCategorie) {
		AnnuncioDTO result = new AnnuncioDTO(annuncioModel.getId(), annuncioModel.getTestoAnnuncio(),
				annuncioModel.getPrezzo(), annuncioModel.getData(), annuncioModel.isAperto(),
				annuncioModel.getUtenteInserimento());

		if (includesCategorie && !annuncioModel.getCategorie().isEmpty())
			result.categorieIds = annuncioModel.getCategorie().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}

	public static List<AnnuncioDTO> createAnnuncioDTOListFromModelList(List<Annuncio> modelListInput,
			boolean includesCategorie) {
		return modelListInput.stream().map(annuncioEntity -> {
			return AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioEntity, includesCategorie);
		}).collect(Collectors.toList());
	}

}
