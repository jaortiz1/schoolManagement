package salesianos.triana.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import salesianos.triana.model.Profesor;
import salesianos.triana.model.Reserva;
import salesianos.triana.model.Sala;
import salesianos.triana.repository.RepositoryReserva;
import salesianos.triana.repository.RepositorySala;

@Service
public class ServiceSala {

	@Autowired
	RepositorySala repositorio;

	@Autowired
	RepositoryReserva repositorioReserva;

	public List<Sala> findAll() {
		List<Sala> salas =repositorio.findAll();
		return repositorio.findAll();
	}

	public Sala buscarPorNombre(String nombre) {
		return repositorio.findFirstByNombre(nombre);
	}

	public Sala save(Sala s) {
		return repositorio.save(s);
	}

	public List<Sala> buscarSalasPorNombre(String nombre) {
		return repositorio.findByNombreIgnoreCaseContaining(nombre);
	}

	public Sala buscarPorId(long id) {
		
		return repositorio.findFirstById(id);
	}

	public boolean comprobarNoExiste(Sala s) {

		boolean noExiste = false;

		if (repositorio.findFirstByNombre(s.getNombre()) == null)
			noExiste = true;

		return noExiste;
	}

	public Sala edit(Sala s) {
		repositorio.delete(s);
		return repositorio.save(s);
	}

	public Sala delete(Sala s) {

		Set<Reserva> reservas = s.getReservas();

		for (Reserva reserva : reservas) {

			repositorioReserva.delete(reserva);

		}
		Sala aBorrar = repositorio.findById(s.getId()).orElse(null);
		if (aBorrar != null)
			repositorio.delete(s);

		return aBorrar;
	}

}
