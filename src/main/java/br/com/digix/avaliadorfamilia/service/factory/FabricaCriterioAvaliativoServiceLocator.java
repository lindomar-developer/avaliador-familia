package br.com.digix.avaliadorfamilia.service.factory;

import br.com.digix.avaliadorfamilia.service.CriterioAvaliativoService;

public interface FabricaCriterioAvaliativoServiceLocator {

    CriterioAvaliativoService getCriterioAvaliativoService(String nome);
}
