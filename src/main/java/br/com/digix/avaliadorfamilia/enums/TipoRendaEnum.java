package br.com.digix.avaliadorfamilia.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoRendaEnum {

    RENDA_BAIXA("RendaBaixa"),
    RENDA_MEDIA("RendaMedia");

    private final String tipo;

}
