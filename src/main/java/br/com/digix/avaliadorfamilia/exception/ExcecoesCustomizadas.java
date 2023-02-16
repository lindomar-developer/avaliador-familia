package br.com.digix.avaliadorfamilia.exception;

import br.com.digix.avaliadorfamilia.enums.MensagemEnum;
import br.com.digix.avaliadorfamilia.helper.MensagensComum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class ExcecoesCustomizadas {

    private final MensagensComum mensagensComum;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<MensagemErro> handleExceptions(Exception ex, WebRequest request){
        return defaultHandle(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FamiliaException.class)
    public final ResponseEntity<MensagemErro> handleExceptions(FamiliaException ex, WebRequest request){
        return defaultHandle(ex, request, ex.getHttpStatus());
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MensagemErro handleExceptions(MethodArgumentNotValidException ex) {
        logError(ex);
        final var listErrors = ex.getBindingResult().getAllErrors().parallelStream()
                .map(objectError -> this.mensagensComum.getMensagem(MensagemEnum.CAMPO_OBRIGATORIO, ((FieldError) objectError).getField(), (objectError).getDefaultMessage()))
                .collect(Collectors.toList());

        return getMensagemErro(listErrors);
    }

    private ResponseEntity<MensagemErro> defaultHandle(final Exception ex, WebRequest request, final HttpStatus httpStatus) {
        logError(ex);
        MensagemErro mensagemErro;
        final Throwable rootException = NestedExceptionUtils.getRootCause(ex);
        mensagemErro = MensagemErro.builder()
                .data(LocalDateTime.now())
                .mensagem(rootException != null ?
                        rootException.getMessage() : ex.getMessage())
                .detalhes(request.getDescription(false))
                .build();

        return new ResponseEntity<>(mensagemErro, httpStatus);
    }

    private MensagemErro getMensagemErro(List<String> errors) {
        return MensagemErro
                .builder()
                .data(LocalDateTime.now())
                .detalhes(this.mensagensComum.getMensagem(MensagemEnum.CAMPOS_COM_ERRO))
                .errors(errors)
                .build();
    }

    private void logError(final Exception e) {
        final String message = String.format("[e]=%s,[m]=%s", e.getClass().getSimpleName(), e.getMessage());
        log.error(message, e);
    }

}
