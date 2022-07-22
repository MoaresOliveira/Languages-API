package io.github.moaresoliveira.linguagens.api.repository;

import io.github.moaresoliveira.linguagens.api.model.Language;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LanguageRepository extends MongoRepository<Language, Integer> {

    Language findLanguagesByNameEqualsIgnoreCase(String name);

}
