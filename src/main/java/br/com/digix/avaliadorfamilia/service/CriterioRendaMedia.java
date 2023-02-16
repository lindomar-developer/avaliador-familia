package br.com.digix.avaliadorfamilia.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("RENDA_MEDIA")
public class CriterioRendaMedia implements CriterioAvaliativoService{

    @Override
    public Integer calcularPontuacaoPelaRenda(BigDecimal rendaFamilia) {
        BigDecimal novescentosUmReais = new BigDecimal("901");
        BigDecimal milQuinhentosReais = new BigDecimal("1500");

        if (rendaFamilia.compareTo(novescentosUmReais) >= 0 && rendaFamilia.compareTo(milQuinhentosReais) <= 0){
            return 3;
        }
        return 0;
    }
}
