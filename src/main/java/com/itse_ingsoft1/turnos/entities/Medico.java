package com.itse_ingsoft1.turnos.entities;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
public class Medico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String especialidad;

  @OneToOne
  @JoinColumn
  private Persona persona;

  @ManyToOne
  @JoinColumn
  private Consultorio consultorio;

  @OneToMany(mappedBy = "medico")
  private Set<JornadaLaboral> jornadasLaborales = new HashSet<>();
}
