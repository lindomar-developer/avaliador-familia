package br.com.digix.avaliadorfamilia.resources.v1;

import br.com.digix.avaliadorfamilia.resources.v1.dto.DependenteDTO;
import br.com.digix.avaliadorfamilia.resources.v1.dto.FamiliaDTO;
import br.com.digix.avaliadorfamilia.service.FamilaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.digix.avaliadorfamilia.util.Constantes.PATH_BASE_FAMILIA;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Familias")
@AllArgsConstructor
@RestController
@RequestMapping(path = PATH_BASE_FAMILIA, produces = APPLICATION_JSON_VALUE)
public class FamilasController {

    private final FamilaService familaService;

    @Operation(summary = "Inclui novos dados de familia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FamiliaDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro servidor",
                    content = @Content)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    FamiliaDTO salvarFamilia(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Requisição da inclusão de nova familia")
                             @RequestBody @Valid final FamiliaDTO familiaDTO) {
        return familaService.salvarFamilia(familiaDTO);
    }

    @Operation(summary = "Atualizar dados da familia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Familia não encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro servidor",
                    content = @Content)})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{idFamilia}")
    void atualizarFamilia(@PathVariable("idFamilia") final Long idFamilia,
                                @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Requisição da alteração dos dados da familia")
                                @RequestBody @Valid final FamiliaDTO familiaDTO) {
        familaService.atualizarFamilia(idFamilia, familiaDTO);
    }

    @Operation(summary = "Buscar familia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FamiliaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Familia não encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro servidor",
                    content = @Content)})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idFamilia}")
    FamiliaDTO buscarFamilia(@PathVariable("idFamilia") final Long idFamilia) {
        return familaService.buscarFamiliaDTOPeloId(idFamilia);
    }

    @Operation(summary = "Excluir dados da familia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Excluido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Familia não encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro servidor",
                    content = @Content)})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idFamilia}")
    void excluirFamilia(@PathVariable("idFamilia") final Long idFamilia) {
        familaService.excluirFamilia(idFamilia);
    }

    @Operation(summary = "Atualizar dados do dependente de uma determinada familia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atualizado dados dos dependentes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FamiliaDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Dados não encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro servidor",
                    content = @Content)})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{idFamilia}/dependentes/{idDependente}")
    void atualuzarDadosDependentes(@PathVariable("idFamilia") final Long idFamilia,
                                   @PathVariable("idDependente") final Long idDependente,
                                   @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Requisição da alteracao de dados dos de dependentes")
                             @RequestBody @Valid final DependenteDTO dependenteDTO) {
        familaService.atualizarDadosDependentes(idFamilia, idDependente, dependenteDTO);
    }

}
