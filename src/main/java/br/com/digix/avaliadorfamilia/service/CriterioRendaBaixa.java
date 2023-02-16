package br.com.digix.avaliadorfamilia.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("RENDA_BAIXA")
public class CriterioRendaBaixa implements CriterioAvaliativoService{

    @Override
    public Integer calcularPontuacaoPelaRenda(BigDecimal rendaFamilia) {
        BigDecimal valorZero = BigDecimal.ZERO;
        BigDecimal valorNovescentosReais = new BigDecimal("900");

        if (rendaFamilia.compareTo(valorZero) >= 0 && rendaFamilia.compareTo(valorNovescentosReais) <= 0){
            return 5;
        }
        return 0;
    }

}
