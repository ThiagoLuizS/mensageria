package br.com.miniautorization.mensageria.exceptionhandler;

import br.com.miniautorization.mensageria.models.dto.ErrorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorView error = createListError(ex.getBindingResult());
        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ErrorView createListError(BindingResult bindingResult){
        return ErrorView.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(bindingResult.getFieldErrors().stream().map(error -> messageSource.getMessage(error, LocaleContextHolder.getLocale())).collect(Collectors.toList()))
                .error(HttpStatus.BAD_REQUEST.name()).build();
    }
}
