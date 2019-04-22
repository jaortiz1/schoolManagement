package salesianos.triana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import salesianos.triana.model.Profesor;
	

public interface RepositoryProfesor extends JpaRepository<Profesor, Long>{
	
	@Query("select p from Profesor p where p.email = ?1 and p.password = ?2")
	public Profesor findFirstByEmailAndPassword(String email, String password);
	
	public Profesor findFirstByEmail(String email);
	
	
	@Query("select p from Profesor p where p.id != ?1")
	public List<Profesor> listaUsuariosSinLogueadoYActivo(long id);
	public List<Profesor> findAllByOrderByEnabledAsc();
	public List<Profesor> findAllOrderByEnabledIsTrue();
	//Buscar
	public List<Profesor> findByEmailIgnoreCaseContaining(String email);
	
	
}
