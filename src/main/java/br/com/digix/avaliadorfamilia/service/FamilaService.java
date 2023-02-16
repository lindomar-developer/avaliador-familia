package br.com.digix.avaliadorfamilia.service;

import br.com.digix.avaliadorfamilia.adapter.DependentesAdapter;
import br.com.digix.avaliadorfamilia.adapter.FamilasAdapter;
import br.com.digix.avaliadorfamilia.domain.Familias;
import br.com.digix.avaliadorfamilia.enums.MensagemEnum;
import br.com.digix.avaliadorfamilia.exception.FamiliaException;
import br.com.digix.avaliadorfamilia.helper.MensagensComum;
import br.com.digix.avaliadorfamilia.repository.DependentesRepository;
import br.com.digix.avaliadorfamilia.repository.FamiliaRepository;
import br.com.digix.avaliadorfamilia.resources.v1.dto.DependenteDTO;
import br.com.digix.avaliadorfamilia.resources.v1.dto.FamiliaDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FamilaService {

    private final FamiliaRepository familiaRepository;
    private final DependentesRepository dependentesRepository;
    private final MensagensComum mensagensComum;

    public FamiliaDTO salvarFamilia(final FamiliaDTO familiaDTO){
        var familias = FamilasAdapter.converterPara(familiaDTO);

        return salvarFamiliaDependente(familiaDTO, familias);
    }

    public void atualizarFamilia(final Long idFamilia, final FamiliaDTO familiaDTO) {
        buscarFamiliaPeloId(idFamilia);

        var familias = FamilasAdapter.converterPara(familiaDTO);
        familias.setId(idFamilia);
        this.familiaRepository.save(familias);
    }

    public FamiliaDTO buscarFamiliaDTOPeloId(final Long idFamilia) {
        return FamilasAdapter.converterPara(buscarFamiliaPeloId(idFamilia));
    }

    public Familias buscarFamiliaPeloId(final Long idFamilia) {
        return this.familiaRepository.findById(idFamilia)
                .orElseThrow(() -> new FamiliaException(HttpStatus.NOT_FOUND,
                        this.mensagensComum.getMensagem(MensagemEnum.OBJETO_NAO_ENCONTRADO, idFamilia)));
    }

    @Transactional
    public FamiliaDTO salvarFamiliaDependente(FamiliaDTO familiaDTO, Familias familias) {
        familiaRepository.save(familias);
        adicionarDependentes(familiaDTO, familias);

        return FamilasAdapter.converterPara(familias);
    }

    private void adicionarDependentes(FamiliaDTO familiaDTO, Familias familias) {
        var dependentes = DependentesAdapter.converterParaListEntity(familiaDTO.getDependentes());
        if (!ObjectUtils.isEmpty(dependentes)){
            dependentes.forEach(familias::adicionaDependente);
            dependentesRepository.saveAll(dependentes);
        }
    }

    @Transactional
    public void excluirFamilia(final Long idFamilia){
        buscarFamiliaPeloId(idFamilia);
        dependentesRepository.excluirDependentesPeloIdFamilia(idFamilia);
        familiaRepository.deleteById(idFamilia);
    }

    @Transactional
    public void atualizarDadosDependentes(final Long idFamilia, final Long idDependente, final DependenteDTO dependenteDTO) {
        var familias = buscarFamiliaPeloId(idFamilia);
        var dependentesSelecionados = this.dependentesRepository.findById(idDependente)
                .orElseThrow(() -> new FamiliaException(HttpStatus.NOT_FOUND,
                        this.mensagensComum.getMensagem(MensagemEnum.OBJETO_NAO_ENCONTRADO, idDependente)));

        if (!dependentesSelecionados.getFamilias().getId().equals(idFamilia)){
            throw new FamiliaException(HttpStatus.BAD_REQUEST,
                    this.mensagensComum.getMensagem(MensagemEnum.OBJETO_VINCULO_EXISTENTE, idDependente));
        }

        var dependentes = DependentesAdapter.converterPara(dependenteDTO);
        dependentes.setId(idDependente);
        dependentes.setFamilias(familias);

        dependentesRepository.save(dependentes);
    }

    public List<Familias> buscarTodasFamilias(){
        return familiaRepository.findAll();
    }
}
