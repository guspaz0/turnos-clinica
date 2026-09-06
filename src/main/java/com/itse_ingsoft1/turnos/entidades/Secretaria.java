package com.itse_ingsoft1.turnos.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Secretaría: usuario con rol SECRETARIA que opera la consulta de agenda (CU-04).
 * Rol sin permisos de escritura sobre turnos.
 */
@Entity
@Table(name = "secretarias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Secretaria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idSecretaria;

  @Column(nullable = false)
  private String nombre;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Builder.Default
  private Rol rol = Rol.SECRETARIA;

  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  /**
   * CU-04: consulta la agenda de un profesional en un rango de fechas.
   * Implementación delegada al servicio de agenda.
   */
  public List<Turno> consultarAgenda(Profesional profesional,
      java.time.LocalDate fechaInicio, java.time.LocalDate fechaFin) {
    return new ArrayList<>(); // delegar a ServicioAgenda
  }

  /**
   * CU-04: filtra u ordena turnos según un criterio.
   */
  public List<Turno> filtrarTurnos(List<Turno> turnos, java.util.function.Predicate<Turno> criterio) {
    return turnos.stream().filter(criterio).toList();
  }
}
