package salesianos.triana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import salesianos.triana.model.Reserva;
import salesianos.triana.model.Sala;

public interface RepositoryReserva extends JpaRepository<Reserva, Long>{
	


}
