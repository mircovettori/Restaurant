package restaurant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import restaurant.domain.Restaurant;
import restaurant.dto.RestaurantDto;
import restaurant.repository.RestaurantRepository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static restaurant.exception.RestException.supplierCreateRestFormattedException;
import static restaurant.exception.RestException.throwRestFormattedException;

@Slf4j
@Service
public class RestaurantService {

  @Autowired
  RestaurantRepository restaurantRepository;

  @Transactional
  public List<Restaurant> getRestaurants(Specification<Restaurant> spec, Sort sort) {
    return restaurantRepository.findAll(spec, sort);
  }

  @Transactional
  public Restaurant getRestaurantById(UUID id) {
    return restaurantRepository.findById(id)
        .orElseThrow(
            supplierCreateRestFormattedException(INTERNAL_SERVER_ERROR, "Restaurant not found")
        );
  }

  @Transactional
  public Restaurant insert(RestaurantDto restaurantDto) {
    if (restaurantDto.getId() != null) {
      throwRestFormattedException(INTERNAL_SERVER_ERROR, "cannot upload new restaurant with id param");
    }
    Restaurant restaurant = Restaurant.of(restaurantDto);
    return restaurantRepository.save(restaurant);
  }

  @Transactional
  public Restaurant updateRestaurant(RestaurantDto restaurantDto) {
    Restaurant restaurant = Restaurant.of(restaurantDto);
    Restaurant restaurantFromDb = getRestaurantById(restaurant.getId());
    restaurant.mergeWith(restaurantFromDb);
    return restaurantRepository.save(restaurant);
  }

  @Transactional
  public void deleteRestaurant(UUID id) {
    restaurantRepository.deleteById(id);
  }

  @Transactional
  public void importFromFile(MultipartFile file) throws IOException {
      ObjectMapper mapper = new ObjectMapper();
      List<RestaurantDto> parsed =
              mapper.readValue(
                      new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8),
                      new TypeReference<List<RestaurantDto>>(){}
              );
      parsed.forEach(this::insert);
  }

}
