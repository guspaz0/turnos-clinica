package com.itse_ingsoft1.turnos.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Profesional (médico) que atiende turnos.
 */
@Entity
@Table(name = "profesionales")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profesional {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idProfesional;

  @Column(nullable = false)
  private String nombre;

  @Column
  private String especialidad;

  @OneToMany(mappedBy = "profesional")
  @Builder.Default
  private List<Horario> horarios = new ArrayList<>();

  @OneToMany(mappedBy = "profesional")
  @Builder.Default
  private List<Turno> turnos = new ArrayList<>();

  /**
   * CU-04: agenda del profesional para una fecha.
   */
  public List<Turno> obtenerAgenda(LocalDate fecha) {
    return turnos.stream()
        .filter(t -> t.getFecha() != null && t.getFecha().equals(fecha))
        .toList();
  }

  /**
   * CU-02: horarios del profesional disponibles para una fecha.
   */
  public List<Horario> obtenerHorariosDisponibles(LocalDate fecha) {
    return horarios.stream()
        .filter(h -> h.getFecha() != null && h.getFecha().equals(fecha))
        .filter(Horario::estaDisponible)
        .toList();
  }
}
