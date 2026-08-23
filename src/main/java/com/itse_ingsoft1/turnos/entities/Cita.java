package com.itse_ingsoft1.turnos.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

import com.itse_ingsoft1.turnos.utiles.EstadoCita;

/**
 *
 * @author gusta
 */

@Entity
@Data
@Builder
public class Cita {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private LocalDateTime fechaHoraInicio;

  @Column
  private LocalDateTime fechaHoraFin;

  @Column
  private EstadoCita estado;

  @Column
  private LocalDateTime createdAt;

  @Column
  private LocalDateTime updatedAt;

  @Column
  private LocalDateTime deletedAt;

  @OneToOne
  @JoinColumn
  private Persona persona;

  @OneToOne
  @JoinColumn
  private Consultorio consultorio;

  @ManyToOne
  @JoinColumn
  private Paciente paciente;

}
