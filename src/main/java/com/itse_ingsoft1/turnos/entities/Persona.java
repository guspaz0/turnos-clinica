package com.itse_ingsoft1.turnos.entities;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
public class Persona {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String nombre;

  @Column
  private String apellido;

  @Column
  private Long documento;

  @Column
  private LocalDate fechaNacimiento;

}
