package com.caridadja.languages.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.caridadja.languages.models.Languages;
import com.caridadja.languages.repositories.LanguageRepository;

@Service
public class LanguageService {

	private final LanguageRepository languageRepository;
	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
	
	
	public List<Languages> allLanguages(){
		return languageRepository.findAll();
	}
	
	public Languages createLanguage(Languages lang) {
		return languageRepository.save(lang);
	}
	
	public Languages findLang(Long id) {
		Optional<Languages> optionalLanguage = languageRepository.findById(id);
		if(optionalLanguage.isPresent()) {
			return optionalLanguage.get();
		}else {
			return null;
		}
	}
	
	public void deleteLanguage(Long id) {
		Optional<Languages> language = languageRepository.findById(id);
		if(language.isPresent()) {
			languageRepository.deleteById(id);
		}
	}
}
