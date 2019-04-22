package salesianos.triana.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "PROFESOR")
public class Profesor implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "DNI")
	private String dni;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "APELLIDOS")
	private String apellidos;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ENABLED")
	private boolean enabled;


	// relaciones
	@OneToMany(mappedBy = "profesor", fetch = FetchType.EAGER)
	Set<Reserva> reservas = new HashSet<Reserva>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "profesor")
	private Set<Authorities> authorities = new HashSet<>();

	public Profesor(long id, String nombre, String dni, String email, String apellidos, String password, boolean enabled,
			boolean superAdmin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.email = email;
		this.apellidos = apellidos;
		this.password = password;
		this.enabled = enabled;
		
		

	}
	

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}


	public Profesor() {


	}
	public Profesor(String nombre, String apellidos, String dni, String email, String password, boolean enabled) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.dni=dni;
		this.email = email;
		this.password = password;
		//el usuario no esta activo hasta que el super admin quiera
		this.enabled = enabled;

	}
	

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return authorities;
	}

	@Override
	public String getUsername() {
		
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}






	

}

