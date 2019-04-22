package salesianos.triana.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import salesianos.triana.formbeans.ReservaSala;
import salesianos.triana.model.Event;
import salesianos.triana.model.Profesor;
import salesianos.triana.model.Reserva;
import salesianos.triana.model.Sala;
import salesianos.triana.repository.RepositoryProfesor;
import salesianos.triana.repository.RepositoryReserva;
import salesianos.triana.repository.RepositorySala;

@Service
public class ServiceReserva {
	@Autowired
	RepositoryReserva repositorio;
	@Autowired
	RepositoryProfesor repoProfesor;
	@Autowired
	RepositorySala repoSala;
	@Autowired
	ServiceSala sala;

	public List<Reserva> findAll() {
		return repositorio.findAll();
	}

	public void delete(Reserva r) {
		repositorio.delete(r);
	}
	public Reserva findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
	public List<Reserva> buscarPorNombreSala(String nombreSala) {
		List<Reserva> listaReservas = new ArrayList<>();
		for (Reserva r:findAll()) {
			if(r.getSala().getNombre().equalsIgnoreCase(nombreSala))
				listaReservas.add(r);
			
		}
		return listaReservas;
	}
	
	/*public Reserva findBySala(String nombreSala) {
		return repoSala.findByNombreIgnoreCaseContaining(nombreSala).;
	}*/


public Reserva ReservaSalaToReserva(ReservaSala rs) {

		LocalDate date = LocalDate.parse(rs.getDiaInicio());
		LocalTime time = LocalTime.parse(rs.getHoraInicio());
		LocalDate date2 = LocalDate.parse(rs.getDiaInicio());
		LocalTime time2 = LocalTime.parse(rs.getHoraFin());
		Reserva r1 = new Reserva();
		LocalDateTime localDateTime = LocalDateTime.of(date, time);
		LocalDateTime localDateTime2 = LocalDateTime.of(date2, time2);
		r1.setFechaHora(localDateTime);
		r1.setFechaHoraEnd(localDateTime2);

		return r1;
	}

//	public List<Event> crearListaEventos(Sala s){
//		
//		for (Reserva r : s.getReservas()) {
//			
//		}
//	}

	public void reservar(Profesor profesor, ReservaSala r) {

		Reserva r1 = ReservaSalaToReserva(r);

		save(r1, profesor, sala.buscarPorNombre(r.getNombreSala()));
	}

	public Reserva save(Reserva r, Profesor p, Sala s) {

		Set<Reserva> reservas = p.getReservas();

		reservas.add(r);

		p.setReservas(reservas);

		repoProfesor.save(p);

		reservas = s.getReservas();

		reservas.add(r);

		repoSala.save(s);

		r.setProfesor(p);

		r.setSala(s);

		return repositorio.save(r);
	}
	
	public boolean comprobarFechaFinalMayorInicial(ReservaSala rs) {
		
		Reserva r1 = ReservaSalaToReserva(rs);
		
		boolean fechaMayor = false;
				
				if(   r1.getFechaHoraEnd().isBefore(r1.getFechaHora())|| r1.getFechaHora().isEqual(r1.getFechaHoraEnd()))
					fechaMayor = true;
		
		return fechaMayor;
		
	}

	public boolean ComprobarFechaMayorQueHoy(ReservaSala rs) {

		Reserva r1 = ReservaSalaToReserva(rs);

		boolean fechaMayor = false;

		if (r1.getFechaHora().isBefore(LocalDateTime.now()))
			fechaMayor = true;

		return fechaMayor;
	}

	public boolean comprobarSolapan(ReservaSala rs) {

		Reserva r1 = ReservaSalaToReserva(rs);

		LocalDateTime fechaIguardada;

		LocalDateTime fechaFguardada;
		
		List<Reserva> listaReservas = new ArrayList<>();
		
		Iterator<Reserva> it = repoSala.findFirstByNombre(rs.getNombreSala()).getReservas().iterator();
		
		Reserva reservaGuardada;
		
		boolean solapanIntervalo = false;
		
		boolean solapanMismaFecha = false;
		
		boolean solapan = false;
		
		while(it.hasNext()) {
			
			listaReservas.add(it.next());
			
		}
		
		
		it = listaReservas.iterator();

		while (it.hasNext() & (!solapanMismaFecha || !solapanIntervalo)) {

			reservaGuardada = it.next();

			fechaIguardada = reservaGuardada.getFechaHora();

			fechaFguardada = reservaGuardada.getFechaHoraEnd();
			
			solapanMismaFecha =(r1.getFechaHora().isEqual(fechaIguardada) || r1.getFechaHora().isEqual(fechaFguardada) || 
					r1.getFechaHoraEnd().isEqual(fechaIguardada) || r1.getFechaHoraEnd().isEqual(fechaFguardada) );
			

			solapanIntervalo = ((r1.getFechaHora().isAfter(fechaIguardada) && r1.getFechaHora().isBefore(fechaFguardada)) ||

					(r1.getFechaHoraEnd().isAfter(fechaIguardada) && r1.getFechaHoraEnd().isBefore(fechaFguardada)));
			
		}
		
		if(solapanMismaFecha || solapanIntervalo)
			solapan = true;

		return solapan;

	}

}
