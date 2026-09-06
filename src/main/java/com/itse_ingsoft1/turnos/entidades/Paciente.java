package com.itse_ingsoft1.turnos.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Paciente: usuario con rol PACIENTE que puede solicitar y cancelar turnos.
 */
@Entity
@Table(name = "pacientes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPaciente;

  @Column(nullable = false)
  private String nombre;

  @Column
  private String telefono;

  @Column(unique = true)
  private String email;

  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @OneToMany(mappedBy = "paciente")
  @Builder.Default
  private List<Turno> turnos = new ArrayList<>();

  /**
   * CU-02 / CU-03: devuelve los turnos del paciente.
   */
  public List<Turno> obtenerTurnos() {
    return turnos;
  }
}
