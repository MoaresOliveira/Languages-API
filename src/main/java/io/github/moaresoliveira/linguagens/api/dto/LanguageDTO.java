package io.github.moaresoliveira.linguagens.api.dto;


import io.github.moaresoliveira.linguagens.api.model.Language;
import lombok.Data;

@Data
public class LanguageDTO {

    private String name;
    private String logo;
    private Integer ranking;

    public Language toEntity(){
        return Language.builder()
                        .name(name)
                        .logo(logo)
                        .ranking(ranking)
                        .votes(0)
                        .build();
    }

}
