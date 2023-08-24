package africa.semicolon.promiscuous.exceptions;

import africa.semicolon.promiscuous.dtos.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {PromiscuousBaseException.class})
    @ResponseStatus(BAD_REQUEST)

    public ResponseEntity<?> handler(PromiscuousBaseException exception) {
        var response = ApiResponse.builder().data(exception.getMessage());
        return ResponseEntity.badRequest().body(response);

    @ExceptionHandler(value = {PromiscuousBaseException.class})
    @ResponseStatus(BAD_REQUEST)

        public ResponseEntity<?> handler(PromiscuousBaseException exception) {
            var response = ApiResponse.builder().data(exception.getMessage());
            return ResponseEntity.badRequest().body(response);


    }
}
