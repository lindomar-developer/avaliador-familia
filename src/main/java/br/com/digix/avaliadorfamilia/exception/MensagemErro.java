package br.com.digix.avaliadorfamilia.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MensagemErro implements Serializable {

    @JsonProperty("data")
    private LocalDateTime data;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("detalhes")
    private String detalhes;

    @JsonProperty("errors")
    @Builder.Default
    private List<String> errors = new ArrayList<>();
}
