package com.itse_ingsoft1.turnos.servicios;

import com.itse_ingsoft1.turnos.dominio.Agenda;
import com.itse_ingsoft1.turnos.entidades.Profesional;
import com.itse_ingsoft1.turnos.entidades.Turno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CU-04: servicio de consulta de agenda (solo lectura para secretaría).
 */
@Service
@RequiredArgsConstructor
public class ServicioAgenda {

  private final Agenda agenda;

  /**
   * CU-04: consulta la agenda de un profesional entre fechaInicio y fechaFin.
   */
  public List<Turno> consultarAgenda(Profesional profesional,
      LocalDate fechaInicio, LocalDate fechaFin) {
    List<Turno> resultado = new ArrayList<>();
    for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
      resultado.addAll(agenda.obtenerTurnos(profesional, fecha));
    }
    return resultado;
  }

  /**
   * CU-04: lista de profesionales del sistema.
   */
  public List<Profesional> obtenerProfesionales() {
    return List.of(); // delegar al repositorio de profesionales
  }
}
