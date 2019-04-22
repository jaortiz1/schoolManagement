package salesianos.triana;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import salesianos.triana.model.Profesor;
import salesianos.triana.model.Reserva;
import salesianos.triana.model.Sala;
import salesianos.triana.service.ServiceAuthoritie;
import salesianos.triana.service.ServiceProfesor;
import salesianos.triana.service.ServiceReserva;
import salesianos.triana.service.ServiceSala;

@SpringBootApplication
public class ProyectoRepasoApplication {
	
	@Autowired
	private ServiceProfesor repo;
	
	@Autowired
	private ServiceProfesor serviceProfesor;
	@Autowired
	private ServiceReserva serviceReserva;
	@Autowired
	private ServiceSala serviceSala;
	
	@Autowired
	private ServiceAuthoritie serviceAuth;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoRepasoApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertInitialData(ServiceSala service) {
		return args -> {
			
			Profesor profesor = new Profesor();
            profesor.setApellidos("PruebaApellidos");
            profesor.setDni("12345678z");
            profesor.setEmail("admin@admin.com");
            profesor.setEnabled(true);
            profesor.setNombre("admin");
            profesor.setPassword("admin");

            Sala s = new Sala();
            Sala s2 = new Sala();
            s.setNombre("Aula01");
            s2.setNombre("Aula02");
            Reserva r1 = new Reserva();

            r1.setFechaHora(LocalDateTime.now());
            r1.setFechaHoraEnd(LocalDateTime.now().plusHours(1));
            
            Reserva r2 = new Reserva();

            r2.setFechaHora(LocalDateTime.now().plusHours(3));
            r2.setFechaHoraEnd(LocalDateTime.now().plusHours(4));

            serviceProfesor.saveAdmin(profesor);

            serviceSala.save(s);
            serviceSala.save(s2);

            serviceReserva.save(r1, profesor, s);
            serviceReserva.save(r2, profesor, s);
            
            
            //usuario standar
            
            Profesor profesor2 = new Profesor();
            profesor2.setApellidos("PruebaApellidos");
            profesor2.setDni("12345678z");
            profesor2.setEmail("usuario@usuario.com");
            profesor2.setEnabled(true);
            profesor2.setNombre("usuario");
            profesor2.setPassword("usuario");

            Sala s4 = new Sala();
            Sala s5 = new Sala();
            s4.setNombre("Aula15");
            s5.setNombre("Aula16");
            Reserva r3 = new Reserva();

            r3.setFechaHora(LocalDateTime.now());
            r3.setFechaHoraEnd(LocalDateTime.now().plusHours(1));
            
            Reserva r4 = new Reserva();

            r4.setFechaHora(LocalDateTime.now().plusHours(3));
            r4.setFechaHoraEnd(LocalDateTime.now().plusHours(4));

            serviceProfesor.save(profesor2);

            serviceSala.save(s4);
            serviceSala.save(s5);

            serviceReserva.save(r3, profesor2, s4);
            serviceReserva.save(r4, profesor2, s5);

			

		};
	}
	@Bean
	public CommandLineRunner init(ApplicationContext appContext) {
		return args -> {
			
				
					

							
						
					
				
			
		};
	}

}
