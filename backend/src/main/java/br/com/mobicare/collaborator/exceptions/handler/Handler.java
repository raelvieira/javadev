package br.com.mobicare.collaborator.exceptions.handler;

import br.com.mobicare.collaborator.exceptions.BadRequestException;
import br.com.mobicare.collaborator.exceptions.ForbiddenException;
import br.com.mobicare.collaborator.exceptions.NotFoundException;
import br.com.mobicare.collaborator.exceptions.response.Error;
import br.com.mobicare.collaborator.exceptions.response.Response;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    public Handler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Error> handleNotFoundException(Exception ex, WebRequest request) {
        Error errorResponse = new Error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<Error> handleForbiddenException(Exception ex, WebRequest request) {
        Error errorResponse = new Error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Error> handleBadRequestException(Exception ex, WebRequest request) {
        Error errorResponse = new Error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        final Response<Error> resp = new Response<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError ->
                resp.add(
                        new Error(fieldError.getDefaultMessage(),
                                ex.getLocalizedMessage())) );

        return super.handleExceptionInternal(ex, resp, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        final Response<Error> resp = new Response<>();
        final String mensage = messageSource
                .getMessage("parametro.invalido", null, new Locale("pt", "BR"));

        resp.add(new Error(mensage, ex.getLocalizedMessage()) );

        return super.handleExceptionInternal(ex, resp, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public Response<Error> handleEmptyResultDataAccess(EmptyResultDataAccessException ex) {
        Response<Error> response = new Response<>();

        String message = String.format("Recurso nao encontrado, esperado %d, encontrado %d ",
                ex.getExpectedSize(), ex.getActualSize());
        response.add(new Error(message, ex.getMostSpecificCause().toString() ));

        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(Response.withDate(new Error("Dados não encontrados!", ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllException(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(Response.withDate(new Error("ERRO INTERNO!", ex.getMessage())));
    }
}
