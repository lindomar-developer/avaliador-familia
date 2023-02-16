package br.com.digix.avaliadorfamilia.resources.v1;

import br.com.digix.avaliadorfamilia.AvaliadorFamiliaApplicationTeste;
import br.com.digix.avaliadorfamilia.util.Constantes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class FamilasControllerTest extends AvaliadorFamiliaApplicationTeste {

    @Test
    @DisplayName("Sucesso ao cadastrar dados de novas familias")
    void test1() {
        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(converterJsonToString("/request/familia-create.json"))
                .post(Constantes.PATH_BASE_FAMILIA)
                .then().assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", equalTo(1))
                .body("rendaTotal", equalTo(500.9F))
                .body("dependentes", hasSize(3))
                .body("dependentes[0].id", equalTo(1))
                .body("dependentes[0].nome", equalTo("Teste 17"))
                .body("dependentes[0].idade", equalTo(17))
                .body("dependentes[1].id", equalTo(2))
                .body("dependentes[1].nome", equalTo("Teste 16"))
                .body("dependentes[1].idade", equalTo(16))
                .body("dependentes[2].id", equalTo(3))
                .body("dependentes[2].nome", equalTo("Teste 18"))
                .body("dependentes[2].idade", equalTo(18));
    }

    @Test
    @DisplayName("Deve retornar erro na falta de campos obrigatorios")
    void test2() {
        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(converterJsonToString("/request/familia-campos-obrigatorios-create.json"))
                .post(Constantes.PATH_BASE_FAMILIA)
                .then().assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detalhes", equalTo("Há campos com erro!"))
                .body("errors[0]", equalTo("Campo rendaTotal é obrigatório."));
    }

    @Test
    @DisplayName("Sucesso ao atualizar dados da familia")
    void test3() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(Constantes.PATH_BASE_FAMILIA+"/2")
                .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(2))
                .body("rendaTotal", equalTo(900F));

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(converterJsonToString("/request/familia-atualizar-campos.json"))
                .put(Constantes.PATH_BASE_FAMILIA+"/2")
                .then().assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(Constantes.PATH_BASE_FAMILIA+"/2")
                .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(2))
                .body("rendaTotal", equalTo(100.5F));
    }

    @Test
    @DisplayName("Erro ao buscar uma familia que não existe")
    void test4() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(Constantes.PATH_BASE_FAMILIA+"/100")
                .then().assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("mensagem", equalTo("O id 100 não foi encontrado na base de dados"));
    }

    @Test
    @DisplayName("Sucesso ao excluir dados da familia")
    void test5() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(Constantes.PATH_BASE_FAMILIA+"/2")
                .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(2))
                .body("rendaTotal", equalTo(900F))
                .body("dependentes", hasSize(3));

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .delete(Constantes.PATH_BASE_FAMILIA+"/2")
                .then().assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(Constantes.PATH_BASE_FAMILIA+"/2")
                .then().assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("mensagem", equalTo("O id 2 não foi encontrado na base de dados"));
    }

    @Test
    @DisplayName("Sucesso alterar dados dos dependentes de uma determinada familia")
    void test6() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(Constantes.PATH_BASE_FAMILIA+"/7")
                .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(7))
                .body("rendaTotal", equalTo(700.00F))
                .body("dependentes[0].id", equalTo(14))
                .body("dependentes[0].nome", equalTo("TESTE 11"))
                .body("dependentes[0].idade", equalTo(18));


        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(converterJsonToString("/request/dependente-atualizar.json"))
                .put(Constantes.PATH_BASE_FAMILIA+"/7/dependentes/14")
                .then().assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get(Constantes.PATH_BASE_FAMILIA+"/7")
                .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(7))
                .body("rendaTotal", equalTo(700.00F))
                .body("dependentes[0].id", equalTo(14))
                .body("dependentes[0].nome", equalTo("DANIEL"))
                .body("dependentes[0].idade", equalTo(15));

    }

}