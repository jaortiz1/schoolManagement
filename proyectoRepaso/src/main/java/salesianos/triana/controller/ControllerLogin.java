package salesianos.triana.controller;

import java.security.Principal;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import salesianos.triana.formbeans.ReservaSala;
import salesianos.triana.model.Profesor;
import salesianos.triana.service.ServiceProfesor;
import salesianos.triana.service.ServiceReserva;
import salesianos.triana.service.ServiceSala;

@Controller
public class ControllerLogin {
	@Autowired
	private ServiceProfesor serviceProfesor;
	@Autowired
	private ServiceReserva serviceReservas;
	
	@Autowired
	private ServiceSala sala;
	
	
	//se muestra la pagina de login
	
	
			@GetMapping("/login")
			public String logueo(Model model) {
				
				model.addAttribute("user", new Profesor());
				
				return "login";
				
			}
			@GetMapping("/")
			public String xd(Model model, Principal principal) {
				Profesor profesor = serviceProfesor.buscarPorEmail(principal.getName());
				Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
		        model.addAttribute("usuarioLogueado", usuarioLogueado);
				
				if(serviceProfesor.comprobarEsAdmin(profesor))
				return "redirect:/admin/gestionSalas";
				String esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
				model.addAttribute("esAdmin", esAdmin);
				model.addAttribute("listaReservas",serviceReservas.findAll());
		        model.addAttribute("listaSalas", sala.findAll());
		        model.addAttribute("beanReserva", new ReservaSala());

		        

				return "redirect:/usuario/gestionSalasVistaUsuario";

				
			}
			
		
	
	
		//coge la peticion de crear cuenta
		@PostMapping("/crearUsuario")
		public String crearUsuario(@ModelAttribute("user") Profesor p, BindingResult bindingResult, Model model, Principal principal)  {
			
			Profesor usuarioLogueado = serviceProfesor.buscarPorEmail(principal.getName());
	        model.addAttribute("usuarioLogueado", usuarioLogueado);
			
	        String esAdmin=serviceProfesor.comprobarEsAdminString(usuarioLogueado);
			model.addAttribute("esAdmin", esAdmin);
			//hasta que el superadmin no te active el usuario no se puede avanzar de pantalla
			if(serviceProfesor.buscarPorEmail(p.getEmail())==null) {
				serviceProfesor.save(p);
				return "redirect:/";
			}else {
		
				

				model.addAttribute("errorUsuario", "El usuario ya existe");
				return "redirect:/";
			}
			
			
			
			
			
			
		}
		
}
