package salesianos.triana.formbeans;

public class Busqueda {
	
	private String campoBusqueda;

	public Busqueda(String campoBusqueda) {
		super();
		this.campoBusqueda = campoBusqueda;
	}

	public Busqueda() {
		
	}
	public String getCampoBusqueda() {
		return campoBusqueda;
	}

	public void setCampoBusqueda(String campoBusqueda) {
		this.campoBusqueda = campoBusqueda;
	}

	@Override
	public String toString() {
		return "Busqueda [campoBusqueda=" + campoBusqueda + "]";
	}
	
	

}
