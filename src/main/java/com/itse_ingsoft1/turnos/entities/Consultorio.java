/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itse_ingsoft1.turnos.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gusta
 */
@Entity
@Data
@Builder
public class Consultorio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String descripcion;

  @OneToMany(mappedBy = "consultorio")
  private Set<Medico> medicos = new HashSet<>();
}
