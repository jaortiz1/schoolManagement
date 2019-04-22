package salesianos.triana.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import salesianos.triana.formbeans.CreacionUsuario;
import salesianos.triana.model.Authorities;
import salesianos.triana.model.Profesor;
import salesianos.triana.model.Reserva;
import salesianos.triana.repository.RepositoryAuthoritie;
import salesianos.triana.repository.RepositoryProfesor;
import salesianos.triana.repository.RepositoryReserva;

@Service
public class ServiceProfesor {
	@Autowired
	RepositoryProfesor repositorio;
	@Autowired
	RepositoryAuthoritie repositorioAuth;
	@Autowired
	RepositoryReserva repositorioReserva;

	public Profesor login(String email, String password) {
		return repositorio.findFirstByEmailAndPassword(email, password);
	}
	// public Profesor obtenerUsuario(LoginUser p) {

	// return new Profesor(p.getEmail(), false, p.getPassword());
	// }

	public Profesor buscarPorEmail(String email) {

		return repositorio.findFirstByEmail(email);
	}

	public boolean comprobarEsAdmin(Profesor profesor) {
		boolean esAdmin = false;

		for (GrantedAuthority auth : profesor.getAuthorities()) {
			if (auth.getAuthority().equalsIgnoreCase("ROLE_ADMIN"))
				esAdmin = true;

		}
		return esAdmin;
	}
	public String comprobarEsAdminString(Profesor profesor) {
		

		for (GrantedAuthority auth : profesor.getAuthorities()) {
			if (auth.getAuthority().equalsIgnoreCase("ROLE_ADMIN"))
				return "si";

		}
		return null;
	}
	
	public void save(Profesor profesor) {

		Set<Authorities> autoridad = new HashSet<>();
		Authorities autoridadProfesor = new Authorities();
		autoridadProfesor.setAuthority("ROLE_USER");
		Set<Authorities> setAutoridades = new HashSet<>();
		setAutoridades.add(autoridadProfesor);
		profesor.setAuthorities(setAutoridades);
		autoridadProfesor.setProfesor(profesor);
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		
		String password = profesor.getPassword();
		
		profesor.setPassword(bcryptPasswordEncoder.encode(password));
		repositorio.save(profesor);
		repositorioAuth.save(autoridadProfesor);

	}
	public void saveAdmin(Profesor profesor) {

		Set<Authorities> autoridad = new HashSet<>();
		Authorities autoridadProfesor = new Authorities();
		Authorities autoridadProfesor2 = new Authorities();
		autoridadProfesor.setAuthority("ROLE_USER");
		autoridadProfesor2.setAuthority("ROLE_ADMIN");
		Set<Authorities> setAutoridades = new HashSet<>();
		setAutoridades.add(autoridadProfesor);
		setAutoridades.add(autoridadProfesor2);
		profesor.setAuthorities(setAutoridades);
		autoridadProfesor.setProfesor(profesor);
		autoridadProfesor2.setProfesor(profesor);
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		
		String password = profesor.getPassword();
		
		profesor.setPassword(bcryptPasswordEncoder.encode(password));
		repositorio.save(profesor);
		repositorioAuth.save(autoridadProfesor);
		repositorioAuth.save(autoridadProfesor2);

	}

	public List<Profesor> listarOrdenadosPorInactivosPrimero() {
		List<Profesor> lista = new ArrayList<Profesor>();
		
		return repositorio.findAllByOrderByEnabledAsc();

	}

	public List<Profesor> listAll() {
		return repositorio.findAll();
	}

	public List<Profesor> listaUsuariosSinLogueadoYActivo(long id) {

		return repositorio.listaUsuariosSinLogueadoYActivo(id);
	}

	public List<Profesor> buscarUsuario(String campoBusqueda) {
		return repositorio.findByEmailIgnoreCaseContaining(campoBusqueda);
	}

	public Profesor findOne(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Profesor delete(Profesor p) {

		Set<Reserva> reservas = p.getReservas();

		for (Reserva reserva : reservas) {

			repositorioReserva.delete(reserva);

		}

		Set<Authorities> autoridades = (Set<Authorities>) p.getAuthorities();

		for (Authorities auth : autoridades) {

			repositorioAuth.delete(auth);

		}

		Profesor aBorrar = repositorio.findById(p.getId()).orElse(null);
		if (aBorrar != null)
			repositorio.delete(p);

		return aBorrar;
	}

	public Profesor edit(Profesor p) {
		repositorio.delete(p);
		p.setEnabled(true);
		return repositorio.save(p);

	}

	// casteo
	public Profesor obtenerUsuarioSinBuscarIdCrear(CreacionUsuario formBean) {

		Profesor u = new Profesor();

		u.setEnabled(true);
		u.setEmail(formBean.getEmail());
		u.setPassword(formBean.getPassword());
		u.setApellidos(formBean.getApellidos());
		u.setDni(formBean.getDni());
		u.setNombre(formBean.getNombre());
		u.setEnabled(true);// cuando el admin los crea se les pone true directamente

		// return new Usuario(formBean.getEmail(), admin, formBean.getPassword());
		return u;

	}

	public Profesor activarUsuario(Profesor profesor) {
		profesor.setEnabled(true);
		return profesor;
	}

}
