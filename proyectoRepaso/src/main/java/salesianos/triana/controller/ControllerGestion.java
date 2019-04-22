package salesianos.triana.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import salesianos.triana.formbeans.Busqueda;
import salesianos.triana.formbeans.CreacionUsuario;
import salesianos.triana.model.Profesor;
import salesianos.triana.service.ServiceProfesor;

@Controller
public class ControllerGestion {

	@Autowired
	private ServiceProfesor serviceProfesor;

	// USUARIOS
	@GetMapping("/admin/gestionUsuarios")
	public String mostrarGestionUsuarios(Model model, Principal principal) {
		String esAdmin;
		Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
		model.addAttribute("lista", serviceProfesor.listarOrdenadosPorInactivosPrimero());

		model.addAttribute("usuarioLogueado", usuarioLogueado);
		model.addAttribute("usuarioBusqueda", new Busqueda());
		esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "/admin/gestionUsuarios";

	}

	// que se vaya el usuario directamente a mis reservas
	// hacer la vista, sera una tabla con las salas y las horas al lado
	// Opciones usuario normal--> mis reservas y salas
	// añadir el boton a mis salas de reservar
	// insertar automaticamente datos iniciales
	// que un usuario no pueda acceder a algunas rutas segun el rol en pagina de
	// config (algo que se parezca a permitAll)

	@PostMapping("/admin/buscarUsuario")
	public String buscarLibro(@ModelAttribute("usuarioBusqueda") Busqueda e, BindingResult bindingResult, Model model,
			Principal principal) {
		Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
		String esAdmin;
		esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		// model.addAttribute("libroBusqueda", new EBookBusqueda());
		model.addAttribute("lista", serviceProfesor.buscarUsuario(e.getCampoBusqueda()));

		return "/admin/gestionUsuarios";
	}

	// AÑADIR USUARIO
	@GetMapping("/admin/aniadirUsuario")
	public String mostrarAniadirUsuario(Model model, Principal principal) {
		String esAdmin;
		model.addAttribute("creacionUsuario", new CreacionUsuario());
		Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		
		esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "/admin/aniadirUsuario";
	}

	@PostMapping("/admin/guardarUsuario")
	public String crearUsuario(@ModelAttribute("creacionUsuario") CreacionUsuario u, BindingResult bindingResult,
			Model model, Principal principal) {
		String esAdmin;
		Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		
		esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		Profesor profesor = serviceProfesor.obtenerUsuarioSinBuscarIdCrear(u);
		// UsuarioDatosPersonales usuarioDatosPersonales =
		// servicio.obtenerUsuarioDatosPersonalesCrear(u);

		if (serviceProfesor.buscarPorEmail(profesor.getEmail()) == null) {
			serviceProfesor.save(profesor);
			return "/admin/aniadirUsuario";
		} else {

			model.addAttribute("lista", serviceProfesor.listaUsuariosSinLogueadoYActivo(usuarioLogueado.getId()));

			model.addAttribute("errorUsuario", "El usuario ya existe");
			return "/admin/aniadirUsuario";
		}

	}
	// EDITARLOS

	@GetMapping("/admin/editarUsuario/{id}")
	public String MostrarEditarUsuario(@PathVariable("id") long id, Model model, Principal principal) {
		String esAdmin;
		Profesor profesor = serviceProfesor.findOne(id);
		Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		model.addAttribute("editProfesor", profesor);
		
		esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);

		return "/admin/editarUsuario";

	}

	@PostMapping("/admin/editarUsuarioPost/{id}")
	public String editarUsuario(@ModelAttribute("editProfesor") Profesor p, BindingResult bindingResult, Model model,
			Principal principal) {
		String esAdmin;
		// p.setId(id);
		serviceProfesor.edit(p);
		Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		model.addAttribute("lista", serviceProfesor.listaUsuariosSinLogueadoYActivo(usuarioLogueado.getId()));

		model.addAttribute("usuarioBusqueda", new Busqueda());
		
		esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "/admin/gestionUsuarios";

	}

	// BORRARLOS JUNTO CON SUS RESERVAS
	@GetMapping("/admin/eliminarUsuario/{id}")
	public String mostrarLogin(@PathVariable("id") long id, Model model, Principal principal) {
		Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		String esAdmin;
		Profesor profesor = serviceProfesor.findOne(id);

		serviceProfesor.delete(profesor);

		model.addAttribute("lista", serviceProfesor.listaUsuariosSinLogueadoYActivo(usuarioLogueado.getId()));

		model.addAttribute("usuarioBusqueda", new Busqueda());
		
		esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "/admin/gestionUsuarios";

	}

	@GetMapping("/admin/activarUsuario/{id}")
	public String activarUsuario(@PathVariable("id") long id, Model model, Principal principal) {

		Profesor profesor = serviceProfesor.findOne(id);
		String esAdmin;
		// Se activa y devuelve el profesor y se vuelve a guardar
		serviceProfesor.edit(serviceProfesor.activarUsuario(profesor));
		Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        
		esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "redirect:/admin/gestionUsuarios";

	}

	@GetMapping("/admin/gestionDiasLectivos")
	public String mostrarGestionDiasLectivos(Model model, Principal principal) {
		String esAdmin;
		Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
		model.addAttribute("usuarioLogueado", usuarioLogueado);
		
		esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
		model.addAttribute("esAdmin", esAdmin);
		return "/admin/gestionDiasLectivos";

	}

}
