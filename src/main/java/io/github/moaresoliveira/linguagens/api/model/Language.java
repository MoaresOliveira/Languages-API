package io.github.moaresoliveira.linguagens.api.model;

import io.github.moaresoliveira.linguagens.api.dto.LanguageDTO;
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

    public void update(LanguageDTO dto){
        this.name = dto.getName();
        this.logo = dto.getLogo();
    }

}
