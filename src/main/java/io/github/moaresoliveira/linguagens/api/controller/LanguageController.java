package io.github.moaresoliveira.linguagens.api.controller;

import io.github.moaresoliveira.linguagens.api.dto.LanguageDTO;
import io.github.moaresoliveira.linguagens.api.model.Language;
import io.github.moaresoliveira.linguagens.api.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    @Autowired
    public LanguageRepository repository;

    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages(){
        Sort sort = Sort.sort(Language.class).by("votes").descending();
        List<Language> languages = repository.findAll(sort);
        if(languages.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        languages.stream().forEach(language -> {
            int ranking = languages.indexOf(language);
            language.setRanking(ranking+1);
        });
        repository.saveAll(languages);
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Language> getLanguageByName(@PathVariable("name") String name){
        Language languages = repository.findLanguagesByNameEqualsIgnoreCase(name);
        return ResponseEntity.ok(languages);
    }

    @PostMapping("/add")
    public ResponseEntity<Language> addLanguage(@RequestBody LanguageDTO language){
        Language languageFound = repository.findLanguagesByNameEqualsIgnoreCase(language.getName());
        if(languageFound != null){
            return ResponseEntity.badRequest().build();
        }
        Language languageSaved = repository.save(language.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(languageSaved);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Language> updateLanguage(@PathVariable("name") String name,@RequestBody LanguageDTO language){
        Language languageFound = repository.findLanguagesByNameEqualsIgnoreCase(name);
        languageFound.update(language);
        Language languageUpdated = repository.save(languageFound);
        return ResponseEntity.ok(languageUpdated);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteLanguageByName(@PathVariable("name") String name){
        Language language = repository.findLanguagesByNameEqualsIgnoreCase(name);
        repository.delete(language);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Language> voteLanguageByName(@PathVariable("name") String name){
        Language languageFound = repository.findLanguagesByNameEqualsIgnoreCase(name);
        Integer votes = languageFound.getVotes();
        languageFound.setVotes(votes == null? 1 : votes+1);
        repository.save(languageFound);
        return ResponseEntity.ok(repository.save(languageFound));
    }
}
