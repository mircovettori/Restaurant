package restaurant.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

@Getter
@Builder
public class RestException extends RuntimeException {

  @Builder.Default
  private HttpStatus status = HttpStatus.BAD_REQUEST;

  @Builder.Default
  private String message = "Please see application log for further details";

  /**
   * Create a RestException with log4j format.
   *
   * @param error code of error
   * @return RestException object
   * @see 'org.slf4' MessageFormatter
   */
  public static RestException createRestFormattedException(
      HttpStatus status, String error) {

    return RestException.builder().message(error).status(status).build();
  }

  /**
   * Create a supplier of RestException with log4j format.
   *
   * @param error code of error
   * @return RestException object
   * @see 'org.slf4' MessageFormatter
   */
  public static Supplier<? extends RestException> supplierCreateRestFormattedException(
      HttpStatus status, String error) {

    return () -> createRestFormattedException(status, error);
  }

  /**
   * Throws a RestException with log4j format.
   *
   * @param error code of error
   * @see 'org.slf4' MessageFormatter
   */
  public static void throwRestFormattedException(
      HttpStatus status, String error) {

    throw createRestFormattedException(status, error);
  }
}
