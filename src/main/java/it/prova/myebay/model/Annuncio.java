package it.prova.myebay.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "annuncio")
public class Annuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "testoAnnuncio")
	private String testoAnnuncio;
	@Column(name = "prezzo")
	private Double prezzo;
	@Column(name = "data")
	private LocalDate data;
	@Column(name = "aperto")
	private boolean aperto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utenteInserimento;
	@ManyToMany
	@JoinTable(name = "annuncio_categoria", joinColumns = @JoinColumn(name="annuncio_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="categoria_id", referencedColumnName = "id"))
	private Set<Categoria> categorie= new HashSet<>(0);
	
	public Annuncio() {
		
	}
	
	public Annuncio(Long id, String testoAnnuncio, Double prezzo, LocalDate data, boolean aperto, Utente utenteInserimento) {
		this.testoAnnuncio=testoAnnuncio;
		this.prezzo=prezzo;
		this.data=data;
		this.aperto=aperto;
		this.utenteInserimento=utenteInserimento;
	}
	
	public Annuncio(Long id, String testoAnnuncio, Double prezzo, LocalDate data, boolean aperto) {
		this.testoAnnuncio=testoAnnuncio;
		this.prezzo=prezzo;
		this.data=data;
		this.aperto=aperto;
	}
	
	public Annuncio(String testoAnnuncio, Double prezzo, LocalDate data, boolean aperto) {
		this.testoAnnuncio=testoAnnuncio;
		this.prezzo=prezzo;
		this.data=data;
		this.aperto=aperto;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}
	
	
	
	
	

}
