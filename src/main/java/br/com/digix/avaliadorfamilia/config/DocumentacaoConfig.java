package br.com.digix.avaliadorfamilia.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        servers = @Server(description = "Avaliador de famílias"),
        info =   @Info(title = "Avaliador de familias", version = "1.0", description = "Documentação da api de avaliação de familias",
                contact = @Contact(name = "Suporte-Digix", email = "suporte-digix@digix.com.br")
        )
)
public class DocumentacaoConfig {
}


