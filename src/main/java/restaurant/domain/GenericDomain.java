package restaurant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class GenericDomain {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private UUID id;

  /** @param source Domain object to merge to actual domain object */
  public <T extends GenericDomain> void mergeWith(T source) {
    FieldUtils.getAllFieldsList(getClass())
        .forEach(
            field -> {
              try {
                field.setAccessible(true);
                if (field.get(this) == null) {
                  field.set(this, field.get(source));
                }
              } catch (IllegalAccessException e) {
                e.printStackTrace();
              }
            });
  }


}
