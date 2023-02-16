package br.com.digix.avaliadorfamilia.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FamiliaException extends RuntimeException{

    private final HttpStatus httpStatus;

    public FamiliaException(final HttpStatus httpStatus, final String error) {
        super(error);
        this.httpStatus = httpStatus;
    }
}
