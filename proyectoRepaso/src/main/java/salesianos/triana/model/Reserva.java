package salesianos.triana.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "RESERVA")
public class Reserva {
	
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "FECHAHORA", nullable = false)
	private LocalDateTime fechaHora;
	@Column(name = "FECHAHORAFIN")
	private LocalDateTime fechaHoraEnd;
	
	
	//Relaciones
	@ManyToOne
	@JoinColumn(name = "PROFESOR_ID")
	private Profesor profesor;
	
	@ManyToOne
	@JoinColumn(name = "SALA_ID")
	private Sala Sala;
	
	public Reserva() {
		
	}

	public Reserva(long id, LocalDateTime fechaHora, LocalDateTime fechaHoraEnd, Profesor profesor,
			salesianos.triana.model.Sala sala) {
		super();
		this.id = id;
		this.fechaHora = fechaHora;
		this.fechaHoraEnd = fechaHoraEnd;
		this.profesor = profesor;
		Sala = sala;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public LocalDateTime getFechaHoraEnd() {
		return fechaHoraEnd;
	}

	public void setFechaHoraEnd(LocalDateTime fechaHoraEnd) {
		this.fechaHoraEnd = fechaHoraEnd;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Sala getSala() {
		return Sala;
	}

	public void setSala(Sala sala) {
		Sala = sala;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaHora=" + fechaHora + ", fechaHoraEnd=" + fechaHoraEnd + "]";
	}

	
	
	
	
	
	

}
