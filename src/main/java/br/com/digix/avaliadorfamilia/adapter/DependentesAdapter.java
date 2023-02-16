package br.com.digix.avaliadorfamilia.adapter;

import br.com.digix.avaliadorfamilia.domain.Dependentes;
import br.com.digix.avaliadorfamilia.resources.v1.dto.DependenteDTO;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DependentesAdapter {

    public static List<Dependentes> converterParaListEntity(final List<DependenteDTO> dependentesRequest){
        if ( !ObjectUtils.isEmpty(dependentesRequest) ){
            return dependentesRequest.stream().map(DependentesAdapter::converterPara).collect(Collectors.toList());
        }
        return null;
    }

    public static Dependentes converterPara(final DependenteDTO dependentesRequest) {
        return Dependentes.builder()
                .nome(dependentesRequest.getNome())
                .idade(dependentesRequest.getIdade())
                .build();
    }

    public static List<DependenteDTO> converterPara(final List<Dependentes> dependentes){
        if ( !ObjectUtils.isEmpty(dependentes) ){
            return dependentes.stream().map(DependentesAdapter::converterPara).collect(Collectors.toList());
        }
        return null;
    }

    private static DependenteDTO converterPara(final Dependentes dependentes) {
        return DependenteDTO.builder()
                .id(dependentes.getId())
                .idade(dependentes.getIdade())
                .nome(dependentes.getNome())
                .build();
    }

}
