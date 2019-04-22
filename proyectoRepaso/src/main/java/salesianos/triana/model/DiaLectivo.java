package salesianos.triana.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIA_LECTIVO")
public class DiaLectivo {
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;
	
	private String dia;
	private boolean lectivo;
	
	/*
	 * El usuario mediante un chexbox seleccionad los dias
	 * cada uno tiene un value: lunes, martes...
	 * se borran todos los dias lectivos de la bd
	 * se introducen todos los dias lectivos con false
	 * se cogen todos con un findall
	 * se activan todos los dias lectivos marcados
	 * se editan
	 * 
	 * 
	 */
}
