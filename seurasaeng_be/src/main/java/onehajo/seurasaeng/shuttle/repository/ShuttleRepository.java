package onehajo.seurasaeng.shuttle.repository;

import onehajo.seurasaeng.entity.Shuttle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShuttleRepository extends JpaRepository<Shuttle, Long> {
}
