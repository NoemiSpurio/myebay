package it.prova.myebay.web.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.annuncio.AnnuncioService;
import it.prova.myebay.service.categoria.CategoriaService;
import it.prova.myebay.service.ruolo.RuoloService;
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

		List<Annuncio> annunci = annuncioService.findByExample(example.buildAnnuncioModel(true));

		model.addAttribute("annunci_list_attribute", AnnuncioDTO.createAnnuncioDTOListFromModelList(annunci, true));
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
			return "annuncio/insert";
		}

		annuncioDTO.setData(new Date());
		annuncioDTO.setAperto(true);
		
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		annuncioDTO.setUtenteInserimento(utenteService.findByUsername(principal.getUsername()));
		annuncioService.inserisciNuovo(annuncioDTO.buildAnnuncioModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Annuncio inserito correttamente");
		return "redirect:/annuncio/list";
	}

	@GetMapping("/show/{idAnnuncio}")
	public String showFilm(@PathVariable(required = true) Long idAnnuncio, Model model) {
		model.addAttribute("show_annuncio_attr",
				AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloElementoEager(idAnnuncio), true));
		return "showAnnuncio";
	}
}
