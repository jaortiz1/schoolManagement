package salesianos.triana.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * Un Rol (podríamos llamarlo autorización) de un usuario.
 * 
 * @author Luismi
 *
 */
@Entity
@Table(name = "AUTHORITIES")
public class Authorities implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;
	
	
	@Column(name = "AUTHORITY")
	private String authority;

	@ManyToOne
	@JoinColumn(name = "PROFESOR_ID")
	private Profesor profesor;

	
	public Authorities() { }


	public Authorities(long id, String authority, Profesor profesor) {
		super();
		this.id = id;
		this.authority = authority;
		this.profesor = profesor;
	}


	public Authorities(String authority, Profesor profesor) {
		super();
		this.authority = authority;
		this.profesor = profesor;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public Profesor getProfesor() {
		return profesor;
	}


	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Authorities [id=" + id + ", authority=" + authority + ", profesor=" + profesor + "]";
	}
	
	
}