/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itse_ingsoft1.turnos.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

/**
 *
 * @author gusta
 */
@Entity
@Data
public class Turno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private LocalDateTime fechaHoraInicio;

  @Column
  private LocalDateTime fechaHoraFin;

  @Column
  private LocalDateTime createdAt;

  @Column
  private LocalDateTime updatedAt;

  @Column
  private LocalDateTime deletedAt;

  @OneToOne
  @JoinColumn
  private Persona persona;

  @OneToOne
  @JoinColumn
  private Consultorio consultorio;

}
