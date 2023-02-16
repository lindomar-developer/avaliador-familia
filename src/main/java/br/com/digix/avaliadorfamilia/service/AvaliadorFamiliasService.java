package br.com.digix.avaliadorfamilia.service;

import br.com.digix.avaliadorfamilia.adapter.FamilasAdapter;
import br.com.digix.avaliadorfamilia.resources.v1.dto.FamiliaDTO;
import br.com.digix.avaliadorfamilia.service.factory.FabricaCriterioAvaliativoServiceLocator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AvaliadorFamiliasService {

    private final FamilaService familaService;

    private FabricaCriterioAvaliativoServiceLocator fabricaCriterioAvaliativoServiceLocator;

    public List<FamiliaDTO> calcularPontuacaoFamilias(){

        final var familias = familaService.buscarTodasFamilias();

        List<FamiliaDTO> familiaDTOS = new ArrayList<>();

        familias.forEach(familia -> {
            CriterioAvaliativoService criterioAvaliativoService = fabricaCriterioAvaliativoServiceLocator
                    .getCriterioAvaliativoService(familia.getRendaTotal().compareTo(new BigDecimal("900")) > 0 ? "RENDA_MEDIA" : "RENDA_BAIXA");

            var pontuacaoRenda = criterioAvaliativoService.calcularPontuacaoPelaRenda(familia.getRendaTotal());
            var pontuacaoNumeroDependentes = 0;
            if (pontuacaoRenda.compareTo(0) > 0){
                pontuacaoNumeroDependentes = criterioAvaliativoService.calcularPontuacaoPelosDependentes(familia);
            }

            var familiaDTO = FamilasAdapter.converterParaFamiliaDTOWithPontuacao(familia, pontuacaoRenda+pontuacaoNumeroDependentes);
            familiaDTOS.add(familiaDTO);
        });

        return familiaDTOS.stream().sorted(Comparator.comparing(FamiliaDTO::getPontuacao).reversed()).collect(Collectors.toList());
    }

}
