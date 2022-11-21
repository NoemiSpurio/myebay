package it.prova.myebay.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.service.acquisto.AcquistoService;
import it.prova.myebay.service.utente.UtenteService;

@Controller
@RequestMapping(value = "/acquisto")
public class AcquistoController {

	@Autowired
	private AcquistoService acquistoService;

	@Autowired
	private UtenteService utenteService;

	@GetMapping("/listAll")
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("acquisti_list_attribute",
				AcquistoDTO.createAcquistoDTOListFromModelList(acquistoService.listAllAcquisti()));
		mv.setViewName("acquisto/list");
		return mv;
	}

	@RequestMapping("/list")
	public String list(AcquistoDTO acquistoExample, ModelMap model, HttpServletRequest request) {
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		acquistoExample.setUtenteAcquirente(utenteService.findByUsername(principal.getUsername()));

		List<Acquisto> acquisti = acquistoService.findByExample(acquistoExample.buildAcquistoModel());
		model.addAttribute("acquisti_list_attribute", AcquistoDTO.createAcquistoDTOListFromModelList(acquisti));

		return "acquisto/list";
	}

	@GetMapping("/search")
	public String search(Model model) {
		return "acquisto/search";
	}

	@GetMapping("/show/{idAcquisto}")
	public String show(HttpServletRequest request, @PathVariable(required = true) Long idAcquisto, Model model) {
		Acquisto acquistoInstance = acquistoService.caricaSingoloAcquisto(idAcquisto);
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		acquistoInstance.setUtente(utenteService.findByUsername(principal.getUsername()));
		AcquistoDTO result = AcquistoDTO.buildAcquistoFromModel(acquistoInstance);

		model.addAttribute("show_acquisto_attr", result);
		return "acquisto/show";
	}
}
