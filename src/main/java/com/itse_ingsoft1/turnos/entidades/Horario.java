package com.itse_ingsoft1.turnos.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Horario de atención de un profesional en una fecha y hora determinadas.
 */
@Entity
@Table(name = "horarios",
    uniqueConstraints = @UniqueConstraint(columnNames = {"profesional_id", "fecha", "hora"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Horario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idHorario;

  @Column(nullable = false)
  private LocalDate fecha;

  @Column(nullable = false)
  private LocalTime hora;

  @Column(nullable = false)
  @Builder.Default
  private boolean disponible = true;

  @ManyToOne
  @JoinColumn(name = "profesional_id")
  private Profesional profesional;

  public boolean estaDisponible() {
    return disponible;
  }
}
