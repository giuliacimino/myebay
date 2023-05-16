package it.prova.myebay.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;

public class AnnuncioDTO {

	private Long id;

	@NotBlank(message = "{testoAnnuncio.notblank}")
	private String testoAnnuncio;
	@NotNull(message = "{prezzo.notnull}")
	private Double prezzo;

	private LocalDate data;

	private boolean aperto;

	private UtenteDTO utente;

	private Long[] categorieIds;
	
	@Autowired
	UtenteDTO utenteDTO;

	public AnnuncioDTO() {

	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Double prezzo, LocalDate data, UtenteDTO utente) {
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.utente=utente;
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Double prezzo, LocalDate data, boolean aperto,
			UtenteDTO utente) {
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utente = utente;
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

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public Long[] getCategorieIds() {
		return categorieIds;
	}

	public void setCategorieIds(Long[] categorieIds) {
		this.categorieIds = categorieIds;
	}

	
	
	public Annuncio buildAnnuncioModel(boolean aperto, boolean includesCategories) {
		
		Utente utenteModel = this.utente != null ? this.utente.buildUtenteModel(true) : null;
		Annuncio result = new Annuncio(this.id, this.testoAnnuncio, this.prezzo, this.data, utenteModel);
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
				UtenteDTO.buildUtenteDTOFromModel(annuncioModel.getUtenteInserimento(), false));

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
