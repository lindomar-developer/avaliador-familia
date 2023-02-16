package br.com.digix.avaliadorfamilia.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensagemEnum {

    OBJETO_NAO_ENCONTRADO("objeto.nao.encontrado"),
    OBJETO_VINCULO_EXISTENTE("erro.objeto.com.vinculo"),


    CAMPOS_COM_ERRO("campos.com.erro"),
    CAMPO_OBRIGATORIO("campo.obrigatorio");

    private final String chaveMsg;

}
