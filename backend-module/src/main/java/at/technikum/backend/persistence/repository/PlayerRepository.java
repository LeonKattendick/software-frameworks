package at.technikum.backend.persistence.repository;

import at.technikum.backend.persistence.model.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, String> {
}
