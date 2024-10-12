package restaurant.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import restaurant.domain.Restaurant;
import restaurant.dto.RestaurantDto;
import restaurant.service.RestaurantService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

  @Autowired
  RestaurantService restaurantService;


  @GetMapping({"/{restaurantId}"})
  public ResponseEntity<Restaurant> getRestaurant(@PathVariable UUID restaurantId) {
    return ResponseEntity.ok(restaurantService.getRestaurantById(restaurantId));
  }

  @GetMapping
  public ResponseEntity<List<Restaurant>> getAllRestaurants() {
    List<Restaurant> restaurants = restaurantService.getRestaurants();
    return ResponseEntity.ok(restaurants);
  }

  @PostMapping
  public ResponseEntity<Restaurant> saveRestaurant(@RequestBody RestaurantDto userDto) {
    Restaurant newRestaurant = restaurantService.insert(userDto);
    return ResponseEntity.ok(newRestaurant);
  }

  //The function receives a PUT request, updates the Restaurant with the specified Id and returns the updated Restaurant
  @PutMapping
  public ResponseEntity<Restaurant> updateRestaurant(@RequestBody RestaurantDto userDto) {
    Restaurant updatedRestaurant = restaurantService.updateRestaurant(userDto);
    return ResponseEntity.ok(updatedRestaurant);
  }

  //The function receives a DELETE request, deletes the Restaurant with the specified Id.
  @DeleteMapping({"/{restaurantId}"})
  public ResponseEntity<Void> deleteRestaurant(@PathVariable("restaurantId") UUID restaurantId) {
    restaurantService.deleteRestaurant(restaurantId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/import")
  public ResponseEntity<Void> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
    restaurantService.importFromFile(file);
    return ResponseEntity.ok().build();
  }

}
