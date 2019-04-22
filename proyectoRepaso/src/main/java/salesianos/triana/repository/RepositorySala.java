package salesianos.triana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import salesianos.triana.model.Profesor;
import salesianos.triana.model.Sala;

public interface RepositorySala extends JpaRepository<Sala, Long>{
	
	public Sala findFirstByNombre(String nombre);
	
	public List<Sala> findByNombreIgnoreCaseContaining(String nombre);
	
	public Sala findFirstById(long id);

}
