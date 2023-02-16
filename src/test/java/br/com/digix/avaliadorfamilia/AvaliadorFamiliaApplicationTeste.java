package br.com.digix.avaliadorfamilia;

import io.restassured.RestAssured;
import net.minidev.json.JSONValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStreamReader;
import java.util.Objects;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static java.nio.charset.StandardCharsets.UTF_8;

@ActiveProfiles("test")
@SpringBootTest(classes = AvaliadorfamiliaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = AvaliadorfamiliaApplication.class)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/scripts/criar-deletar-tabelas.sql", "/scripts/popular-tabelas.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/scripts/limpar-tabelas.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public abstract class AvaliadorFamiliaApplicationTeste {

    @LocalServerPort
    private int port;

    @BeforeAll
    public void setup() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected String converterJsonToString(final String path) {
        return JSONValue.parse(
                        new InputStreamReader(Objects.requireNonNull(getClass().
                                getResourceAsStream(path)), UTF_8))
                .toString();
    }
}
