package com.itse_ingsoft1.turnos.entities;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.LocalDate;

@Entity
@Data
public class JornadaLaboral {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String descripcion;

  @Column
  private LocalDate fecha;

}
