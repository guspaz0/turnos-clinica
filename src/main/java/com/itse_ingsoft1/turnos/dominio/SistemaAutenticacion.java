package com.itse_ingsoft1.turnos.dominio;

import com.itse_ingsoft1.turnos.entidades.Rol;
import com.itse_ingsoft1.turnos.entidades.Usuario;

/**
 * Contrato del sistema de autenticación (CU-01).
 */
public interface SistemaAutenticacion {

  /**
   * Valida las credenciales de un usuario.
   *
   * @param usuario  nombre de usuario (email)
   * @param password contraseña en texto plano
   * @return true si las credenciales son válidas
   */
  boolean validarCredenciales(String usuario, String password);

  /**
   * Obtiene el rol de un usuario.
   *
   * @param usuario nombre de usuario (email)
   * @return rol del usuario
   */
  Rol obtenerRol(String usuario);
}
