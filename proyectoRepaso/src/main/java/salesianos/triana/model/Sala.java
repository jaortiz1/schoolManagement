package salesianos.triana.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SALA")
public class Sala {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NOMBRE")
	private String nombre;

	// relaciones
	@OneToMany(mappedBy = "Sala", fetch = FetchType.EAGER)
	Set<Reserva> reserva = new HashSet<Reserva>();

	public Sala() {

	}

	public Sala(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	public Set<Reserva> getReservas() {
		return reserva;
	}

	public void setReserva(Set<Reserva> reserva) {
		this.reserva = reserva;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	



	public void setReservas(Set<Reserva> reservas) {
		this.reserva = reservas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



}
