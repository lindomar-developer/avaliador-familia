package br.com.digix.avaliadorfamilia.adapter;

import br.com.digix.avaliadorfamilia.domain.Familias;
import br.com.digix.avaliadorfamilia.resources.v1.dto.FamiliaDTO;

public class FamilasAdapter {

    public static Familias converterPara(final FamiliaDTO familiaDTO){
        return Familias.builder()
                .rendaTotal(familiaDTO.getRendaTotal())
                .build();
    }

    public static FamiliaDTO converterPara(final Familias familias){
        return FamiliaDTO.builder()
                .id(familias.getId())
                .rendaTotal(familias.getRendaTotal())
                .dependentes(DependentesAdapter.converterPara(familias.getDependentes()))
                .build();
    }

    public static FamiliaDTO converterParaFamiliaDTOWithPontuacao(final Familias familias, final Integer pontuacao){
        return FamiliaDTO.builder()
                .id(familias.getId())
                .rendaTotal(familias.getRendaTotal())
                .dependentes(DependentesAdapter.converterPara(familias.getDependentes()))
                .pontuacao(pontuacao)
                .build();
    }


}
