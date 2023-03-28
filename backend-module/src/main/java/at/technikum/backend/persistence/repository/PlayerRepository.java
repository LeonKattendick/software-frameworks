package at.technikum.backend.persistence.repository;

import at.technikum.backend.persistence.model.PlayerEntity;
import at.technikum.commons.schema.unified.GameType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, String> {

    List<PlayerEntity> findAll();

    List<PlayerEntity> findAllByGameType(GameType type);

}
