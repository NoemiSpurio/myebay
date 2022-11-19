package it.prova.myebay.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.annuncio.AnnuncioService;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {

	@Autowired
	private AnnuncioService annuncioService;
	
	@GetMapping("/search")
	public String search() {
		return "annuncio/search";
	}
	
	@PostMapping("/list")
	public String list(AnnuncioDTO example, ModelMap model) {
		
		List<Annuncio> annunci = annuncioService.findByExample(example.buildAnnuncioModel());
		
		model.addAttribute("annunci_list_attribute", AnnuncioDTO.createAnnuncioDTOListFromModelList(annunci));
		return "annuncio/list";
	}
}
