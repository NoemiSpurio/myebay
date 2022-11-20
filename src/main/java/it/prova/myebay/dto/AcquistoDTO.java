package it.prova.myebay.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Utente;

public class AcquistoDTO {

	private Long id;
	private String descrizione;
	private Date data;
	private Integer prezzo;
	private Utente utenteAcquirente;

	public AcquistoDTO() {

	}

	public AcquistoDTO(Long id, String descrizione, Date data, Integer prezzo, Utente utenteAcquirente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
		this.utenteAcquirente = utenteAcquirente;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Utente getUtenteAcquirente() {
		return utenteAcquirente;
	}

	public void setUtenteAcquirente(Utente utenteAcquirente) {
		this.utenteAcquirente = utenteAcquirente;
	}

	public static AcquistoDTO buildAcquistoFromModel(Acquisto acquistoModel) {
		return new AcquistoDTO(acquistoModel.getId(), acquistoModel.getDescrizione(), acquistoModel.getData(),
				acquistoModel.getPrezzo(), acquistoModel.getUtente());
	}

	public static List<AcquistoDTO> createAcquistoDTOListFromModelList(List<Acquisto> list) {
		return list.stream().map(a -> AcquistoDTO.buildAcquistoFromModel(a)).collect(Collectors.toList());
	}
	
	public static List<AcquistoDTO> buildAcquistoDtoListFromModelList(List<Acquisto> models) {
		return models.stream().map(acquisto -> {
			return AcquistoDTO.buildAcquistoFromModel(acquisto);
		}).collect(Collectors.toList());
	}
}
