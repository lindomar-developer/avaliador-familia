package br.com.digix.avaliadorfamilia.resources.v1.dto;


import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Value
@With
@Jacksonized
@Builder
@ToString
public class FamiliaDTO {

    Long id;

    @NotNull(message = "{campo.obrigatorio}")
    BigDecimal rendaTotal;

    @Valid
    List<DependenteDTO> dependentes;

    Integer pontuacao;
}
