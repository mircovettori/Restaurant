package restaurant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import restaurant.dto.RestaurantDto;

import java.util.UUID;


@Entity
@Table(name = "restaurant", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Restaurant extends GenericDomain {

  @Column(name = "town_hall", nullable = false)
  private String townHall;

  @Column(name = "province", nullable = false)
  private String province;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "street", nullable = false)
  private String street;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "postal_code", nullable = false)
  private String postalCode;

  @Column(name = "mail")
  private String mail;

  @Column(name = "issuer")
  private String issuer;

  @Column(name = "phone")
  private String phone;

  @Column(name = "fax")
  private String fax;

  @Column(name = "latitude", nullable = false)
  private String latitude;

  @Column(name = "longitude", nullable = false)
  private String longitude;


  public static Restaurant of(RestaurantDto restaurantDto) {
    Restaurant restaurant = Restaurant.builder()
            .townHall(restaurantDto.getTownHall())
            .province(restaurantDto.getProvince())
            .type(restaurantDto.getType())
            .name(restaurantDto.getName())
            .street(restaurantDto.getStreet())
            .city(restaurantDto.getCity())
            .postalCode(restaurantDto.getPostalCode())
            .mail(restaurantDto.getPostalCode())
            .issuer(restaurantDto.getIssuer())
            .phone(restaurantDto.getPhone())
            .fax(restaurantDto.getFax())
            .latitude(restaurantDto.getLatitude())
            .longitude(restaurantDto.getLongitude())
            .build();
    if(restaurantDto.getId() != null) {
      restaurant.setId(UUID.fromString(restaurantDto.getId()));
    }
    return restaurant;
  }
}
