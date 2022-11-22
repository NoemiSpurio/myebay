package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.myebay.dto.UtenteChangePwdDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.utente.UtenteService;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/changePwd")
	public String changePwd(Model model) {
		model.addAttribute("utente_cambia_pwd", new UtenteChangePwdDTO());
		return "utente/formChangePwd";
	}

	@PostMapping("/executeChangePwd")
	public String execChangePwd(@ModelAttribute("utente_cambia_pwd") UtenteChangePwdDTO utenteInstance, Model model) {

		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utente utenteInSessione = utenteService.findByUsername(principal.getUsername());

		if (utenteInstance.getPassword().isBlank() || utenteInstance.getConfermaPassword().isBlank()
				|| utenteInstance.getVecchiaPassword().isBlank()) {
			model.addAttribute("errorMessage", "Sono presenti errori di validazione!");
			return "utente/formChangePwd";
		}
		
		if (!passwordEncoder.matches(utenteInstance.getVecchiaPassword(), utenteInSessione.getPassword())) {
			model.addAttribute("errorMessage", "La vecchia password e' sbagliata!");
			return "utente/formChangePwd";
		}
		
		if (!utenteInstance.getPassword().equals(utenteInstance.getConfermaPassword())) {
			model.addAttribute("errorMessage", "La password non coincide con la conferma!");
			return "utente/formChangePwd";
		}
		
		utenteService.changePwd(utenteInstance, utenteInSessione);

		return "redirect:/executeLogout";
	}
}
