package br.com.caique.desafiozup.config;

import br.com.caique.desafiozup.DesafiozupApplication;
import br.com.caique.desafiozup.dto.ErroFormularioDto;
import br.com.caique.desafiozup.exception.FabricanteInvalidoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidaFormularioHandler {

    @Autowired
    private MessageSource messageSource;

    private static Logger logger = LoggerFactory.getLogger(DesafiozupApplication.class);

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormularioDto> handle(MethodArgumentNotValidException exception) {
        List<ErroFormularioDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(err -> {
            String mensagem= messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErroFormularioDto erroFormularioDto = new ErroFormularioDto(err.getField(), mensagem);
            dto.add(erroFormularioDto);
        });

        return dto;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public void handle(IncorrectResultSizeDataAccessException exception) {
        logger.warn("Falha na consulta: " + exception.getMessage());
    }

}
