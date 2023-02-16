package br.com.digix.avaliadorfamilia.resources.v1;

import br.com.digix.avaliadorfamilia.resources.v1.dto.FamiliaDTO;
import br.com.digix.avaliadorfamilia.service.AvaliadorFamiliasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.digix.avaliadorfamilia.util.Constantes.PATH_AVALIADOR_FAMILIA;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Familias")
@AllArgsConstructor
@RestController
@RequestMapping(path = PATH_AVALIADOR_FAMILIA, produces = APPLICATION_JSON_VALUE)
public class AvaliadorFamiliaController {

    private final AvaliadorFamiliasService avaliadorFamiliasService;

    @Operation(summary = "Lista as familias com base na pontuação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FamiliaDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Erro servidor",
                    content = @Content)})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<FamiliaDTO> listarFamilia() {
        return avaliadorFamiliasService.calcularPontuacaoFamilias();
    }

}
