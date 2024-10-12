package restaurant.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;


/**
 * @author OITECH develop team - 07/03/2019
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestJsonError {

  private Integer status;

  private String error;

}