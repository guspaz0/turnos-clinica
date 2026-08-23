/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itse_ingsoft1.turnos.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author gusta
 */
@Entity
@Data
public class Consultorio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String descripcion;

  @ManyToMany
  @JoinColumn
  private Medico medicos;

  @OneToMany
  @JoinColumn
  private Turno turnos;
}
