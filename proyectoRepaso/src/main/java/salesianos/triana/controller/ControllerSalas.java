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
import salesianos.triana.model.Profesor;
import salesianos.triana.model.Reserva;
import salesianos.triana.model.Sala;
import salesianos.triana.service.ServiceProfesor;
import salesianos.triana.service.ServiceSala;

@Controller
public class ControllerSalas {

	@Autowired
	private ServiceSala serviceSalas;
	@Autowired
	private ServiceProfesor profe;
	@GetMapping("/admin/gestionSalas")
	public String mostrarSalas(Model model, Principal principal) {
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
		model.addAttribute("listaSalas", serviceSalas.findAll());
		model.addAttribute("salaBusqueda", new Busqueda());
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "/admin/gestionSalas";

	}
	@GetMapping("/usuario/gestionSalasVistaUsuario")
	public String mostrarSalasUsuario(Model model, Principal principal) {
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
		model.addAttribute("listaSalas", serviceSalas.findAll());
		model.addAttribute("salaBusqueda", new Busqueda());
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		
		return "/usuario/gestionSalasVistaUsuario";

	}

	@PostMapping("/admin/buscarSala")
	public String buscarSala(@ModelAttribute("salaBusqueda") Busqueda s, BindingResult bindingResult, Model model, Principal principal) {
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
		model.addAttribute("listaSalas", serviceSalas.buscarSalasPorNombre(s.getCampoBusqueda()));
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "/admin/gestionSalas";
	}

	@GetMapping("/admin/aniadirSala")
	public String mostrarAniadirUsuario(Model model, Principal principal) {
		model.addAttribute("nuevaSala", new Sala());
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "/admin/aniadirSala";
	}

	@PostMapping("/admin/guardarSala")
	public String crearSala(@ModelAttribute("nuevaSala") Sala s, BindingResult bindingResult, Model model, Principal principal) {
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		if (serviceSalas.comprobarNoExiste(s)) {
			serviceSalas.save(s);
			model.addAttribute("salaBusqueda", new Busqueda());
			model.addAttribute("listaSalas", serviceSalas.findAll());
			return "/admin/gestionSalas";
		} else {
			model.addAttribute("errorSala", "La sala ya existe");

			return "/admin/aniadirSala";

		}

	}

	@GetMapping("/admin/editarSala/{id}")
	public String MostrarEditarUsuario(@PathVariable("id") long id, Model model, Principal principal) {
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
		Sala sala = serviceSalas.buscarPorId(id);
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		model.addAttribute("editSala", sala);

		return "/admin/editarSala";

	}

	@PostMapping("/admin/editarSalaPost/{id}")
	public String editarSala(@ModelAttribute("editSala") Sala s, BindingResult bindingResult, Model model, Principal principal) {
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
		if (serviceSalas.comprobarNoExiste(s)) {
			serviceSalas.edit(s);
			model.addAttribute("salaBusqueda", new Busqueda());
			model.addAttribute("listaSalas", serviceSalas.findAll());
			return "/admin/gestionSalas";
		} else {
			model.addAttribute("errorSala", "La sala ya existe");

			return "/admin/aniadirSala";

		}

	}

	@GetMapping("/admin/eliminarSala/{id}")
	public String eliminarSala(@PathVariable("id") long id, Model model, Principal principal) {
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
		Sala sala = serviceSalas.buscarPorId(id);
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		serviceSalas.delete(sala);

		

		model.addAttribute("salaBusqueda", new Busqueda());

		return "/admin/gestionSalas";

	}
	@GetMapping("/usuario/reservasSalas")
	public String detallesReservasSalas(Model model, Principal principal) {
		Profesor usuarioLogueado = profe.buscarPorEmail(principal.getName());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
		String esAdmin=profe.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		model.addAttribute("salaBusqueda", new Busqueda());

		
		return "/usuario/reservasSala";

	}
	
}
