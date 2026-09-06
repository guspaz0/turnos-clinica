package com.itse_ingsoft1.turnos.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Turno agendado de un paciente con un profesional.
 */
@Entity
@Table(name = "turnos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Turno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idTurno;

  @Column(nullable = false)
  private LocalDate fecha;

  @Column(nullable = false)
  private LocalTime hora;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Builder.Default
  private EstadoTurno estado = EstadoTurno.AGENDADO;

  @ManyToOne
  @JoinColumn(name = "paciente_id")
  private Paciente paciente;

  @ManyToOne
  @JoinColumn(name = "profesional_id")
  private Profesional profesional;

  @OneToOne
  @JoinColumn(name = "horario_id")
  private Horario horario;

  /**
   * CU-03: cancela el turno (solo si puede cancelarse).
   */
  public void cancelar() {
    if (!puedeCancelar()) {
      throw new IllegalStateException("El turno no puede ser cancelado");
    }
    this.estado = EstadoTurno.CANCELADO;
  }

  /**
   * Marca el turno como realizado (ej. al vencer su fecha).
   */
  public void marcarRealizado() {
    this.estado = EstadoTurno.REALIZADO;
  }

  /**
   * CU-02: true si el turno ocupa su horario (no cancelado).
   */
  public boolean estaOcupado() {
    return estado == EstadoTurno.AGENDADO || estado == EstadoTurno.REALIZADO;
  }

  /**
   * CU-03: solo se puede cancelar un turno AGENDADO cuya fecha no haya vencido.
   */
  public boolean puedeCancelar() {
    return estado == EstadoTurno.AGENDADO
        && (fecha == null || !fecha.isBefore(LocalDate.now()));
  }
}
