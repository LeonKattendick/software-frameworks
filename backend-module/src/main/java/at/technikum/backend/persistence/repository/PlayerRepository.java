package at.technikum.backend.persistence.repository;

import at.technikum.backend.persistence.model.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerEntity, String> {
}
