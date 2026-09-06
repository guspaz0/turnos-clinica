package com.itse_ingsoft1.turnos.dominio;

import com.itse_ingsoft1.turnos.entidades.*;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Agenda central del sistema: gestiona turnos y horarios de los profesionales.
 */
@Component
public class Agenda {

  @Getter
  private final List<Turno> turnos = new ArrayList<>();

  /**
   * CU-02: registra un turno en la agenda.
   */
  public void registrarTurno(Turno turno) {
    turnos.add(turno);
    if (turno.getHorario() != null) {
      turno.getHorario().setDisponible(false);
    }
  }

  /**
   * CU-03: elimina un turno de la agenda y libera su horario.
   */
  public void eliminarTurno(Turno turno) {
    turnos.remove(turno);
    if (turno.getHorario() != null) {
      turno.getHorario().setDisponible(true);
    }
  }

  /**
   * CU-04: turnos de un profesional para una fecha.
   */
  public List<Turno> obtenerTurnos(Profesional profesional, LocalDate fecha) {
    return turnos.stream()
        .filter(t -> t.getProfesional() == profesional
            && t.getFecha() != null && t.getFecha().equals(fecha))
        .toList();
  }

  /**
   * CU-02: horarios disponibles de un profesional para una fecha.
   */
  public List<Horario> obtenerHorariosDisponibles(Profesional profesional, LocalDate fecha) {
    return profesional.getHorarios().stream()
        .filter(h -> h.getFecha() != null && h.getFecha().equals(fecha))
        .filter(Horario::estaDisponible)
        .toList();
  }

  /**
   * CU-02: true si ya existe un turno activo para ese profesional, fecha y hora.
   */
  public boolean existeDuplicado(Profesional profesional, LocalDate fecha, java.time.LocalTime hora) {
    return turnos.stream().anyMatch(t ->
        t.getProfesional() == profesional
            && t.getFecha() != null && t.getFecha().equals(fecha)
            && t.getHora() != null && t.getHora().equals(hora)
            && t.estaOcupado());
  }
}
