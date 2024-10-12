package restaurant.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import restaurant.exception.RestException;
import restaurant.json.RestJsonError;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

  /**
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(RestException.class)
  public ResponseEntity<RestJsonError> handleRestException(RestException ex, WebRequest request) {

    RestJsonError jsonError =
        RestJsonError.builder()
            .status(ex.getStatus().value())
            .error(ex.getMessage())
            .build();
    log.error("HTTP {} - {}", ex.getStatus(), ex.getMessage(), ex.getCause());

    return ResponseEntity.status(jsonError.getStatus()).body(jsonError);
  }

  /**
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<RestJsonError> handleException(RestException ex, WebRequest request) {

    RestJsonError jsonError =
        RestJsonError.builder()
            .status(INTERNAL_SERVER_ERROR.value())
            .error(ex.getMessage())
            .build();
    log.error("HTTP {} - {}", ex.getStatus(), ex.getMessage(), ex.getCause());

    return ResponseEntity.status(jsonError.getStatus()).body(jsonError);
  }

}
