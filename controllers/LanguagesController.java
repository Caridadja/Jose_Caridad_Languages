package com.caridadja.languages.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.caridadja.languages.models.Languages;
import com.caridadja.languages.services.LanguageService;

@Controller
public class LanguagesController {
	private final LanguageService languageService;

	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}
	@RequestMapping("/")
	public String redirectToIndex() {
		return "redirect:/languages";
	}

	@RequestMapping("/languages")
	public String index(Model model, @ModelAttribute("language") Languages language) {
		List<Languages> languages = languageService.allLanguages();
		model.addAttribute("languages", languages);
		return "index.jsp";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("language") Languages language, BindingResult result) {
		if (result.hasErrors()) {
			return "index.jsp";
		} else {
			languageService.createLanguage(language);
			return "redirect:/languages";
		}
	}

	@RequestMapping("/languages/{id}")
	public String showLang(@PathVariable("id") Long id, Model model) {
		Languages language = languageService.findLang(id);
		model.addAttribute("language", language);
		return "show.jsp";
	}

	// delete routing
	@RequestMapping("/languages/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		languageService.deleteLanguage(id);
		return "redirect:/languages";
	}

	// edit routing
	@RequestMapping("/languages/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		// edit a form
		Languages language = languageService.findLang(id);
		model.addAttribute("lang", language);
		return "edit.jsp";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("lang") Languages lang, BindingResult result) {
		languageService.createLanguage(lang);
		return "redirect:/languages";
				
	}
}