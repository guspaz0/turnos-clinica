package com.itse_ingsoft1.turnos.entities;

import lombok.*;
import jakarta.persistence.*;
import com.itse_ingsoft1.turnos.utiles.*;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Tabla de los horarios en los que los medicos estan disponibles para atender
 */
@Entity
@Data
@Builder
public class JornadaLaboral {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String descripcion;

  @Column
  private Turno turno;

  @Column
  private LocalDateTime inicio;

  @Column
  private LocalDateTime fin;

  @ManyToOne
  @JoinColumn
  private Medico medico;
}
