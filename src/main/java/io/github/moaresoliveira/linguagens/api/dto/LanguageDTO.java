package io.github.moaresoliveira.linguagens.api.dto;


import io.github.moaresoliveira.linguagens.api.model.Language;
import lombok.Data;

@Data
public class LanguageDTO {

    private String name;
    private String logo;
    private Integer ranking;
    private Integer votes;

    public Language toEntity(){
        return Language.builder()
                        .name(name)
                        .logo(logo)
                        .ranking(Integer.MAX_VALUE)
                        .votes(0)
                        .build();
    }

}
