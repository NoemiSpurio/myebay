package it.prova.myebay.web.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.exceptions.CreditoInsufficienteException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.acquisto.AcquistoService;
import it.prova.myebay.service.annuncio.AnnuncioService;
import it.prova.myebay.service.categoria.CategoriaService;
import it.prova.myebay.service.utente.UtenteService;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {

	@Autowired
	private AnnuncioService annuncioService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private AcquistoService acquistoService;

	@GetMapping("/list")
	public ModelAndView listAllAnnunci() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("annuncio_list_attribute",
				AnnuncioDTO.createAnnuncioDTOListFromModelList(annuncioService.listAllAnnunci(), false));
		mv.setViewName("annuncio/list");
		return mv;
	}

	@GetMapping("/search")
	public String search(Model model) {
		model.addAttribute("categorie_totali_attr",
				CategoriaDTO.createCategoriaDTOListFromModelSet(categoriaService.listAllCategorie()));
		model.addAttribute("annuncio_search", new AnnuncioDTO());
		return "annuncio/search";
	}

	@PostMapping("/list")
	public String list(AnnuncioDTO example, ModelMap model) {

		if (example.isUtenteLoggato()) {
			UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			example.setUtenteInserimento(utenteService.findByUsername(principal.getUsername()));
		}

		List<Annuncio> annunci = annuncioService.findByExample(example.buildAnnuncioModel(true));

		model.addAttribute("annunci_list_attribute", AnnuncioDTO.createAnnuncioDTOListFromModelList(annunci, true));

		if (example.isUtenteLoggato()) {
			return "utente/myAnnunci";
		}

		return "annuncio/list";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("categorie_totali_attr",
				CategoriaDTO.createCategoriaDTOListFromModelSet(categoriaService.listAllCategorie()));
		model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		return "annuncio/insert";
	}

	@PostMapping("/save")
	public String saveAnnuncio(@Validated @ModelAttribute("insert_annuncio_attr") AnnuncioDTO annuncioDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			model.addAttribute("categorie_totali_attr",
					CategoriaDTO.createCategoriaDTOListFromModelSet(categoriaService.listAllCategorie()));
			return "/annuncio/insert";
		}

		annuncioDTO.setData(new Date());
		annuncioDTO.setAperto(true);

		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		annuncioDTO.setUtenteInserimento(utenteService.findByUsername(principal.getUsername()));
		annuncioService.inserisciNuovo(annuncioDTO.buildAnnuncioModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Annuncio inserito correttamente");

		return "redirect:/annuncio/listUtente";
	}
	
	@RequestMapping("/listUtente")
	public String listAnnunciUtente(HttpServletRequest request, Annuncio annuncioExample, ModelMap model) {
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		annuncioExample.setUtenteInserimento(utenteService.findByUsername(principal.getUsername()));
		
		model.addAttribute("annunci_list_attribute", AnnuncioDTO
				.createAnnuncioDTOListFromModelList(annuncioService.findByExample(annuncioExample), false));

		return "utente/myAnnunci";
	}

	@GetMapping("/show/{idAnnuncio}")
	public String showFilm(@PathVariable(required = true) Long idAnnuncio, Model model) {
		model.addAttribute("show_annuncio_attr", annuncioService.caricaSingoloElementoEager(idAnnuncio));
		return "annuncio/show";
	}

	@GetMapping("/mysearch")
	public String mysearch(Model model) {
		model.addAttribute("categorie_totali_attr",
				CategoriaDTO.createCategoriaDTOListFromModelSet(categoriaService.listAllCategorie()));
		model.addAttribute("annuncio_search", new AnnuncioDTO());

		return "utente/mieiAnnunciSearch";
	}

	@GetMapping("/delete/{idAnnuncio}")
	public String delete(@PathVariable(required = true) Long idAnnuncio, Model model, HttpServletRequest request) {
		AnnuncioDTO annuncioDTO = AnnuncioDTO
				.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloAnnuncio(idAnnuncio), false);
		model.addAttribute("delete_annuncio_attr", annuncioDTO);
		return "annuncio/delete";
	}

	@PostMapping("/executeDelete")
	public String remove(@RequestParam Long idAnnuncio, RedirectAttributes redirectAttrs, Model model) {

		annuncioService.rimuovi(idAnnuncio);

		List<Annuncio> annunci = annuncioService.listAllAnnunci();
		model.addAttribute("annunci_list_attribute", AnnuncioDTO.createAnnuncioDTOListFromModelList(annunci, true));
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/annuncio/list";
	}

	@GetMapping("/update/{idAnnuncio}")
	public String edit(@PathVariable(required = true) Long idAnnuncio, Model model, HttpServletRequest request) {
		AnnuncioDTO annuncioDTO = AnnuncioDTO
				.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloElementoConCategorie(idAnnuncio), false);
		annuncioDTO.setCategorie(annuncioService.caricaSingoloElementoConCategorie(idAnnuncio).getCategorie());
		model.addAttribute("update_annuncio_attr", annuncioDTO);
		model.addAttribute("categorie_totali_attr",
				CategoriaDTO.createCategoriaDTOListFromModelSet(categoriaService.listAllCategorie()));
		return "annuncio/update";
	}

	@PostMapping("/executeUpdate")
	public String update(@Valid @ModelAttribute("update_annuncio_attr") AnnuncioDTO annuncioDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("categorie_totali_attr",
					CategoriaDTO.createCategoriaDTOListFromModelSet(categoriaService.listAllCategorie()));
			return "annuncio/update";
		}

		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		annuncioDTO.setUtenteInserimento(utenteService.findByUsername(principal.getUsername()));
		annuncioService.aggiorna(annuncioDTO.buildAnnuncioModel(true));

		List<Annuncio> annunci = annuncioService.listAllAnnunci();
		model.addAttribute("annunci_list_attribute", AnnuncioDTO.createAnnuncioDTOListFromModelList(annunci, true));
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/annuncio/list";
	}

	@PostMapping("/compra")
	public String compra(@RequestParam Long idAnnuncio, Model model, RedirectAttributes redirectAttrs,
			HttpServletRequest request) {

		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utente utenteLoggato = utenteService.findByUsername(principal.getUsername());

		try {
			annuncioService.compra(idAnnuncio, utenteLoggato);
		} catch (CreditoInsufficienteException ex) {
			redirectAttrs.addFlashAttribute("errorMessage", "Credito insufficiente.");
			return "redirect:/annuncio/list";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("errorMessage", "Si e' verificato un errore.");
			return "redirect:/annuncio/list";
		}

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		model.addAttribute("acquisti_list_attribute", AcquistoDTO
				.createAcquistoDTOListFromModelList(acquistoService.findAllAcquistiEagerUtente(utenteLoggato.getId())));
		return "redirect:/acquisto/list";
	}
	
	@GetMapping("/acquistaWithoutAuth")
	public String acquistaWithoutAuth(@RequestParam(required = true) Long idAnnuncioWithNoAuth,
			Model model, RedirectAttributes redirectAttrs,HttpServletRequest request, Principal principal) {
		
		if (principal != null) {
			return this.compra(idAnnuncioWithNoAuth, model, redirectAttrs, request);
		}
		
		model.addAttribute("idAnnuncioWithNoAuth", idAnnuncioWithNoAuth);
		return "/login";
	}
}
