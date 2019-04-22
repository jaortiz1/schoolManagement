package salesianos.triana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import salesianos.triana.model.Authorities;
import salesianos.triana.repository.RepositoryAuthoritie;

@Service
public class ServiceAuthoritie {
	
	@Autowired
	RepositoryAuthoritie repositorio;
	
	public void save(Authorities auth) {
		repositorio.save(auth);
	}
	
	public void delete(Authorities auth) {
		repositorio.delete(auth);
	}

}
