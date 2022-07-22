package io.github.moaresoliveira.linguagens.api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("LanguageAPI")
public class Language {

    @Id
    private String id;
    private String name;
    private String logo;
    private Integer ranking;
    private Integer votes;

}
