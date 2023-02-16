package br.com.digix.avaliadorfamilia.service;

import br.com.digix.avaliadorfamilia.domain.Familias;

import java.math.BigDecimal;
import java.util.Objects;

public interface CriterioAvaliativoService {

    Integer NUMERO_DEPENDENTES_USADO_PARA_CORTE = 3;
    Integer PONTUACAO_MAXIMA = 3;
    Integer PONTUACAO_MINIMA = 2;

    Integer calcularPontuacaoPelaRenda(BigDecimal bigDecimal);

    default Integer calcularPontuacaoPelosDependentes(Familias familias){
        if (Objects.isNull(familias.getDependentes())) {
            return 0;
        }

        long totalDependentesMenoresDeDezoitoAnos = familias.getDependentes().stream()
                .filter(dependentes -> dependentes.getIdade().compareTo(18) <= 0)
                .count();

        if (totalDependentesMenoresDeDezoitoAnos == 0){
            return 0;
        }

        return NUMERO_DEPENDENTES_USADO_PARA_CORTE > totalDependentesMenoresDeDezoitoAnos ? PONTUACAO_MINIMA : PONTUACAO_MAXIMA;
    };

}

