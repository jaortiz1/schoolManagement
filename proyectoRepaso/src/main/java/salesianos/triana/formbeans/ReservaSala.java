package salesianos.triana.formbeans;

public class ReservaSala {

	private String diaInicio;
	private String diaFin;
	private String horaInicio;
	private String horaFin;
	private String nombreSala;
	
	private long id;
	
	public ReservaSala() {
		
	}

	public ReservaSala(String diaInicio, String diaFin, String horaInicio, String horaFin, String nombreSala, long id) {
		super();
		this.diaInicio = diaInicio;
		this.diaFin = diaFin;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.nombreSala = nombreSala;
		this.id = id;
	}

	public String getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(String diaInicio) {
		this.diaInicio = diaInicio;
	}

	public String getDiaFin() {
		return diaFin;
	}

	public void setDiaFin(String diaFin) {
		this.diaFin = diaFin;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	
}
