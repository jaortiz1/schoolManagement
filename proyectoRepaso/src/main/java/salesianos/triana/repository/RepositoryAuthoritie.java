package salesianos.triana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import salesianos.triana.model.Authorities;
import salesianos.triana.model.Profesor;

public interface RepositoryAuthoritie extends JpaRepository<Authorities, Long>{

}
