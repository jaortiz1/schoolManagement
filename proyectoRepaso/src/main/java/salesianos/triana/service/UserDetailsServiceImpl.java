package salesianos.triana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import salesianos.triana.model.Profesor;
import salesianos.triana.repository.RepositoryProfesor;
/**
 * Este servicio es importante, ya que es el que va a ser capaz
 * de "unir" nuestro modelo (y repositorio) con el esquema de
 * autenticación de Spring Security.
 * 
 * Implementa a UserDetailsService, lo que nos implica sobrescribir
 * el método loadUserByUsername, que será el que utilice nuestro 
 * AuthenticationManagerBuilder.
 * 
 * A través de este método, localizamos a nuestro usuario con sus roles
 * para devolver una instancia de UserDetails. Esta es la que
 * se utiliza más adelante como Principal. 
 * 
 * @author Luismi
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ServiceProfesor repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Buscamos el usuario por su nombe de usuario
		Profesor profesor = repository.buscarPorEmail(email);

		if (profesor == null) {
			// En caso de no localizarlo, lanzamos una excepción
			throw new UsernameNotFoundException("User not found");

		} 

		// Devolvemos la instancia de UserDetails.
		return profesor;

	}

}
