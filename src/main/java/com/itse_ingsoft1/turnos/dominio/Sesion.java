package com.itse_ingsoft1.turnos.dominio;

import com.itse_ingsoft1.turnos.entidades.Usuario;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Sesión de autenticación de un usuario (CU-01).
 */
@Getter
public class Sesion {

  private final Usuario usuario;
  private final LocalDateTime fechaInicio;
  private boolean activa;

  public Sesion(Usuario usuario) {
    this.usuario = usuario;
    this.fechaInicio = LocalDateTime.now();
    this.activa = true;
  }

  /**
   * Cierra la sesión.
   */
  public void cerrar() {
    this.activa = false;
  }

  /**
   * @return true si la sesión sigue activa
   */
  public boolean estaActiva() {
    return activa;
  }
}
