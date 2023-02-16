package br.com.digix.avaliadorfamilia.resources.v1.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;

@Value
@With
@Jacksonized
@Builder
@ToString
public class DependenteDTO {

    Long id;

    @NotNull(message = "{campo.obrigatorio}")
    String nome;

    @NotNull(message = "{campo.obrigatorio}")
    Integer idade;
}
