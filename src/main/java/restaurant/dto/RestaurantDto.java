package restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDto {

  @JsonProperty("id")
  private String id;

  @JsonProperty(value = "ccomune", required = true)
  private String townHall;

  @JsonProperty(value = "cprovincia", required = true)
  private String province;

  @JsonProperty(value = "ctipologia",required = true)
  private String type;

  @JsonProperty(value = "cnome",required = true)
  private String name;

  @JsonProperty(value = "cvia",required = true)
  private String street;

  @JsonProperty(value = "ccitta",required = true)
  private String city;

  @JsonProperty(value = "ccap",required = true)
  private String postalCode;

  @JsonProperty("cindirizzo_posta_elettronica")
  private String mail;

  @JsonProperty("corgano_validante")
  private String issuer;

  @JsonProperty("ctelefono")
  private String phone;

  @JsonProperty("cfax")
  private String fax;

  @JsonProperty(value = "clatitudine", required = true)
  private String latitude;

  @JsonProperty(value = "clongitudine", required = true)
  private String longitude;
}
