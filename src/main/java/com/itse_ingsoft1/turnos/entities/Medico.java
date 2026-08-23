package com.itse_ingsoft1.turnos.entities;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Data
public class Medico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String especialidad;

  @OneToOne
  @JoinColumn
  private Persona persona;

  @OneToMany
  @Column
  private JornadaLaboral jornadasLaborales;
}
