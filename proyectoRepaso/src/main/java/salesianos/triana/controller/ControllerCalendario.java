package salesianos.triana.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import salesianos.triana.formbeans.Busqueda;
import salesianos.triana.formbeans.ReservaSala;
import salesianos.triana.model.Profesor;
import salesianos.triana.service.ServiceProfesor;
import salesianos.triana.service.ServiceReserva;
import salesianos.triana.service.ServiceSala;

@Controller
//@RequestMapping("/usuario")
public class ControllerCalendario {

	@Autowired
	private ServiceReserva serviceReservas;
	@Autowired
	private ServiceProfesor profe;
	@Autowired
	private ServiceSala sala;

	// CALENDARIO

	
	
	@GetMapping("/admin/calendario/{id}")

	public String mostrarCalendarioAdmin(@PathVariable("id") long id, Model model, Principal principal) {

		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		model.addAttribute("listaSalas", sala.findAll());
		model.addAttribute("beanReserva", new ReservaSala());
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		model.addAttribute("reservas", sala.buscarPorId(id).getReservas());
		
		return "/admin/detallesSalasAdmin";

	}
	/*@GetMapping("/usuario/calendario/{id}")

	public String mostrarCalendarioUsuario(@PathVariable("id") long id, Model model, Principal principal) {

		model.addAttribute("listaReservas", serviceReservas.findAll());
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		model.addAttribute("listaSalas", sala.findAll());
		model.addAttribute("beanReserva", new ReservaSala());
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		model.addAttribute("reservas", sala.buscarPorId(id).getReservas());
		
		return "/admin/detallesSalas";

	}*/
	@GetMapping("/usuario/calendario/{id}")
	public String reservarSala(@PathVariable("id") long id, Model model, Principal principal) {

		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		model.addAttribute("listaSalas", sala.findAll());
		model.addAttribute("beanReserva", new ReservaSala());
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		model.addAttribute("reservas", sala.buscarPorId(id).getReservas());
		
		return "/usuario/detallesSalas";

	}
	
	
	

	/// usuario/reservarSala

	@PostMapping("/usuario/reservarSala/")
	public String reservarSala(@ModelAttribute("beanReserva") ReservaSala r, BindingResult bindingResult, Model model, Principal principal)  {
		  Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
	      model.addAttribute("listaReservas",serviceReservas.findAll());
	      model.addAttribute("usuarioLogueado", usuarioLogueado);
	       
	        model.addAttribute("listaSalas", sala.findAll());
	        model.addAttribute("beanReserva", new ReservaSala());
	        String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
			model.addAttribute("esAdmin", esAdmin);
	        if (serviceReservas.comprobarFechaFinalMayorInicial(r)) {
				model.addAttribute("errorFechaFinalMayorInicial", "1");
				return "/usuario/detallesSalas";
			}  
	      

		if (serviceReservas.ComprobarFechaMayorQueHoy(r)) {
			model.addAttribute("errorFechaMenor", "1");
			return "/usuario/detallesSalas";
		}

		if (serviceReservas.comprobarSolapan(r)) {
			model.addAttribute("errorFechaSolapa", "1");
			return "/usuario/detallesSalas";
		}
		
		

		Profesor profesor = profe.buscarPorEmail(principal.getName());
		serviceReservas.reservar(profesor, r);
		
		return "redirect:/usuario/gestionSalasVistaUsuario";

	}
	// USUARIOS NORMALES

	@GetMapping("/usuario/misReservas")
	public String mostrarMisSalas(Model model, Principal principal) {
		model.addAttribute("busqueda", new Busqueda());
		Profesor profesor = profe.buscarPorEmail(principal.getName());
		String esAdmin=profe.comprobarEsAdminString(profesor);
		model.addAttribute("esAdmin", esAdmin);
		model.addAttribute("usuarioLogueado", profesor);
		model.addAttribute("listaReservas", profesor.getReservas());
		return "/usuario/misReservas";

	}

	@GetMapping("/usuario/eliminarReserva/{id}")
	public String eliminarReserva(@PathVariable("id") long id, Model model, Principal principal) {

		model.addAttribute("busqueda", new Busqueda());
		Profesor profesor = profe.buscarPorEmail(principal.getName());
		model.addAttribute("listaReservas", profesor.getReservas());
		String esAdmin=profe.comprobarEsAdminString(profesor);
		model.addAttribute("esAdmin", esAdmin);
        model.addAttribute("usuarioLogueado", profesor);

		serviceReservas.delete(serviceReservas.findById(id));
		return "redirect:/usuario/misReservas";

	}

	@PostMapping("/usuario/buscarReserva/")
	public String buscarReserva(@ModelAttribute("busqueda") Busqueda e, BindingResult bindingResult, Model model,
			Principal principal) {
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		model.addAttribute("listaReservas", serviceReservas.buscarPorNombreSala(e.getCampoBusqueda()));
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "/admin/gestionUsuarios";
	}

}
