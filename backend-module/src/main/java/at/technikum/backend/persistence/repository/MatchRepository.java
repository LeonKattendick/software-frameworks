package at.technikum.backend.persistence.repository;

import at.technikum.backend.persistence.model.MatchEntity;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository  extends CrudRepository<MatchEntity, String> {
}
