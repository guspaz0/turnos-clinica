package com.itse_ingsoft1.turnos.entities;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
public class Paciente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn
  private Persona persona;

  @OneToMany(mappedBy = "paciente")
  private Set<Cita> citas = new HashSet<>();
}
