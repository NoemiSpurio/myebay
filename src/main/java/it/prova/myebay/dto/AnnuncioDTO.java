package it.prova.myebay.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;

public class AnnuncioDTO {

	private Long id;

	@NotBlank(message = "{annuncio.testoAnnuncio.notblank}")
	private String testoAnnuncio;

	@NotNull(message = "{annuncio.prezzo.notnull}")
	@Min(0)
	private Integer prezzo;

	private Date data;

	private boolean aperto;

	private Utente utenteInserimento;
	
	private boolean utenteLoggato;

	private Long[] categorieIds;

	public AnnuncioDTO() {

	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Integer prezzo, Date data, boolean aperto,
			Utente utenteInserimento) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utenteInserimento = utenteInserimento;
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Integer prezzo) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Integer prezzo, Date data, boolean aperto,
			Utente utenteInserimento, Long[] categorieIds) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utenteInserimento = utenteInserimento;
		this.categorieIds = categorieIds;
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

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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

	public Annuncio buildAnnuncioModel(boolean includeIdCategorie) {
		Annuncio result = new Annuncio(this.id, this.testoAnnuncio, this.prezzo, this.data, this.aperto,
				this.utenteInserimento);
		if (includeIdCategorie && categorieIds != null) {
			result.setCategorie(
					Arrays.asList(categorieIds).stream().map(id -> new Categoria(id)).collect(Collectors.toSet()));
		}

		return result;
	}

	public static AnnuncioDTO buildAnnuncioDTOFromModel(Annuncio annuncioModel, boolean includeCategories) {
		AnnuncioDTO result = new AnnuncioDTO(annuncioModel.getId(), annuncioModel.getTestoAnnuncio(),
				annuncioModel.getPrezzo(), annuncioModel.getData(), annuncioModel.isAperto(),
				annuncioModel.getUtenteInserimento());

		if (includeCategories && !annuncioModel.getCategorie().isEmpty()) {
			result.categorieIds = annuncioModel.getCategorie().stream().map(c -> c.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});
		}
		
		return result;
	}

	public static List<AnnuncioDTO> createAnnuncioDTOListFromModelList(List<Annuncio> modelListInput,
			boolean includeCategories) {
		return modelListInput.stream().map(annuncioEntity -> {
			return AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioEntity, includeCategories);
		}).collect(Collectors.toList());
	}

	public boolean isUtenteLoggato() {
		return utenteLoggato;
	}

	public void setUtenteLoggato(boolean utenteLoggato) {
		this.utenteLoggato = utenteLoggato;
	}
}
