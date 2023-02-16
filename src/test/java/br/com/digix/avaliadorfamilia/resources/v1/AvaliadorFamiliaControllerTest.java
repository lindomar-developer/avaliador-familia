package br.com.digix.avaliadorfamilia.resources.v1;

import br.com.digix.avaliadorfamilia.AvaliadorFamiliaApplicationTeste;
import br.com.digix.avaliadorfamilia.util.Constantes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

class AvaliadorFamiliaControllerTest extends AvaliadorFamiliaApplicationTeste {

    @Test
    @DisplayName("Sucesso listar as familias ordenadas pela sua pontuação")
    void test1() {
        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(Constantes.PATH_AVALIADOR_FAMILIA)
                .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(8));
    }

}