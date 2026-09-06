package com.itse_ingsoft1.turnos.servicios;

import com.itse_ingsoft1.turnos.dominio.Agenda;
import com.itse_ingsoft1.turnos.entidades.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CU-02 / CU-03: servicio de gestión de turnos.
 */
@Service
@RequiredArgsConstructor
public class ServicioTurnos {

  private final Agenda agenda;

  /**
   * CU-02: solicita un turno para un paciente con un profesional en un horario.
   *
   * @return el turno registrado
   * @throws IllegalStateException si el horario no está disponible
   */
  public Turno solicitarTurno(Paciente paciente, Profesional profesional, Horario horario) {
    if (!verificarHorarioDisponible(profesional, horario)) {
      throw new IllegalStateException("El horario no está disponible");
    }

    Turno turno = Turno.builder()
        .paciente(paciente)
        .profesional(profesional)
        .horario(horario)
        .fecha(horario.getFecha())
        .hora(horario.getHora())
        .estado(EstadoTurno.AGENDADO)
        .build();

    agenda.registrarTurno(turno);
    paciente.getTurnos().add(turno);
    return turno;
  }

  /**
   * CU-03: cancela un turno del paciente y libera el horario.
   *
   * @throws IllegalStateException si el turno no puede cancelarse
   */
  public void cancelarTurno(Paciente paciente, Turno turno) {
    if (!turno.getPaciente().equals(paciente)) {
      throw new IllegalStateException("El turno no pertenece al paciente");
    }
    if (!turno.puedeCancelar()) {
      throw new IllegalStateException("El turno no puede ser cancelado");
    }
    turno.cancelar();
    agenda.eliminarTurno(turno);
  }

  /**
   * CU-02: true si el horario está disponible para el profesional.
   */
  public boolean verificarHorarioDisponible(Profesional profesional, Horario horario) {
    return horario.estaDisponible()
        && horario.getProfesional() == profesional
        && !agenda.existeDuplicado(profesional, horario.getFecha(), horario.getHora());
  }

  /**
   * CU-02: profesionales con disponibilidad para asignar turnos.
   */
  public List<Profesional> obtenerProfesionalesDisponibles() {
    return List.of(); // delegar al repositorio de profesionales
  }
}
