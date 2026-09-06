package com.itse_ingsoft1.turnos;

import com.itse_ingsoft1.turnos.dominio.Agenda;
import com.itse_ingsoft1.turnos.dominio.Sesion;
import com.itse_ingsoft1.turnos.entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntitiesTest {

  private Agenda agenda;
  private Profesional profesional;
  private Paciente paciente;
  private Horario horario;

  @BeforeEach
  void setup() {
    agenda = new Agenda();
    profesional = Profesional.builder().nombre("Dra. Lopez").especialidad("Cardiologia").build();
    paciente = Paciente.builder().nombre("Juan Perez").build();
    horario = Horario.builder()
        .fecha(LocalDate.of(2026, 7, 15))
        .hora(LocalTime.of(10, 0))
        .profesional(profesional)
        .disponible(true)
        .build();
    profesional.getHorarios().add(horario);
  }

  @Test
  void turnoPuedeCancelarseSiEstaAgendadoYVigente() {
    Turno turno = Turno.builder()
        .fecha(LocalDate.now().plusDays(1))
        .hora(LocalTime.of(10, 0))
        .estado(EstadoTurno.AGENDADO)
        .build();
    assertTrue(turno.puedeCancelar());
    turno.cancelar();
    assertEquals(EstadoTurno.CANCELADO, turno.getEstado());
  }

  @Test
  void turnoRealizadoNoPuedeCancelarse() {
    Turno turno = Turno.builder()
        .fecha(LocalDate.now().minusDays(1))
        .hora(LocalTime.of(10, 0))
        .estado(EstadoTurno.REALIZADO)
        .build();
    assertFalse(turno.puedeCancelar());
    assertThrows(IllegalStateException.class, turno::cancelar);
  }

  @Test
  void agendaRegistraYEliminaTurno() {
    Turno turno = Turno.builder()
        .fecha(horario.getFecha())
        .hora(horario.getHora())
        .profesional(profesional)
        .paciente(paciente)
        .horario(horario)
        .estado(EstadoTurno.AGENDADO)
        .build();
    agenda.registrarTurno(turno);

    assertTrue(agenda.existeDuplicado(profesional, horario.getFecha(), horario.getHora()));
    assertFalse(horario.estaDisponible());
    assertEquals(1, agenda.obtenerTurnos(profesional, horario.getFecha()).size());

    agenda.eliminarTurno(turno);
    assertTrue(horario.estaDisponible());
    assertTrue(agenda.obtenerTurnos(profesional, horario.getFecha()).isEmpty());
  }

  @Test
  void usuarioSeBloqueaTrasTresIntentosFallidos() {
    Usuario usuario = Usuario.builder()
        .nombre("Test")
        .email("test@mail.com")
        .passwordHash("hash123")
        .rol(Rol.PACIENTE)
        .build();

    assertFalse(usuario.autenticar("test@mail.com", "clave-falsa"));
    assertFalse(usuario.autenticar("test@mail.com", "clave-falsa"));
    assertFalse(usuario.estaBloqueado());
    assertFalse(usuario.autenticar("test@mail.com", "clave-falsa"));
    assertTrue(usuario.estaBloqueado());
    usuario.desbloquear();
    assertFalse(usuario.estaBloqueado());
  }

  @Test
  void sesionSeCierra() {
    Usuario usuario = Usuario.builder().email("u@mail.com").rol(Rol.SECRETARIA).build();
    Sesion sesion = new Sesion(usuario);
    assertTrue(sesion.estaActiva());
    sesion.cerrar();
    assertFalse(sesion.estaActiva());
  }

  @Test
  void profesionalFiltraAgendaYHorariosPorFecha() {
    LocalDate fecha = LocalDate.of(2026, 7, 15);
    Turno turno = Turno.builder()
        .fecha(fecha)
        .hora(LocalTime.of(10, 0))
        .profesional(profesional)
        .estado(EstadoTurno.AGENDADO)
        .build();
    profesional.getTurnos().add(turno);

    assertEquals(1, profesional.obtenerAgenda(fecha).size());
    assertTrue(profesional.obtenerAgenda(fecha.plusDays(1)).isEmpty());
    assertEquals(1, profesional.obtenerHorariosDisponibles(fecha).size());
    assertTrue(profesional.obtenerHorariosDisponibles(fecha.plusDays(1)).isEmpty());
  }
}
