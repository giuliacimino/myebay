package it.prova.myebay.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "utente")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "dateCreated")
	private LocalDate dateCreated;
	@Column(name = "creditoResiduo")
	private Double creditoResiduo;
	@Enumerated(EnumType.STRING)
	private StatoUtente stato;
	
	@OneToMany
	@JoinTable(name="utente_annuncio", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "id" ), inverseJoinColumns =@JoinColumn(name="annuncio_id", referencedColumnName = "id" ))
	private Set<Annuncio> annunci= new HashSet<>(0);
	@OneToMany
	@JoinTable(name = "utente_acquisto", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="acquisto_id", referencedColumnName = "id"))
	private Set<Acquisto> acquisti= new HashSet<>(0);
	
	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>(0);
	
	
	public Utente() {
		
	}
	
	public Utente(Long id, String username, String password, String nome, String cognome, LocalDate dateCreated, Double creditoResiduo, StatoUtente stato) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.nome=nome;
		this.cognome=cognome;
		this.dateCreated=dateCreated;
		this.creditoResiduo=creditoResiduo;
		this.stato=stato;
	}
	
	public Utente(String username, String password, String nome, String cognome, Double creditoResiduo) {
		this.username=username;
		this.password=password;
		this.nome=nome;
		this.cognome=cognome;
		this.creditoResiduo=creditoResiduo;
	}
	
	public Utente (String username, String password) {
		this.username=username;
		this.password=password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Double getCreditoResiduo() {
		return creditoResiduo;
	}

	public void setCreditoResiduo(Double creditoResiduo) {
		this.creditoResiduo = creditoResiduo;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Set<Annuncio> getAnnunci() {
		return annunci;
	}

	public void setAnnunci(Set<Annuncio> annunci) {
		this.annunci = annunci;
	}

	public Set<Acquisto> getAcquisti() {
		return acquisti;
	}

	public void setAcquisti(Set<Acquisto> acquisti) {
		this.acquisti = acquisti;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	
	public boolean isAdmin() {
		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(Ruolo.ROLE_ADMIN))
				return true;
		}
		return false;
	}

	public boolean isAttivo() {
		return this.stato != null && this.stato.equals(StatoUtente.ATTIVO);
	}

	public boolean isDisabilitato() {
		return this.stato != null && this.stato.equals(StatoUtente.DISABILITATO);
	}

	
	
	
	
	
	

}
