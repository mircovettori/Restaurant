package restaurant.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import restaurant.domain.Restaurant;

import java.util.UUID;

public interface RestaurantRepository extends CrudRepository<Restaurant, UUID>, PagingAndSortingRepository<Restaurant, UUID>, JpaSpecificationExecutor<Restaurant> {}//extends JpaRepository<Restaurant, UUID>, JpaRepositoryImplementation<Restaurant, UUID> {}
