package com.itse_ingsoft1.turnos.servicios;

import com.itse_ingsoft1.turnos.dominio.Sesion;
import com.itse_ingsoft1.turnos.dominio.SistemaAutenticacion;
import com.itse_ingsoft1.turnos.entidades.Rol;
import com.itse_ingsoft1.turnos.entidades.Usuario;
import com.itse_ingsoft1.turnos.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CU-01: servicio de autenticación.
 */
@Service
@RequiredArgsConstructor
public class ServicioAutenticacion {

  private final SistemaAutenticacion sistemaAutenticacion;
  private final UsuarioRepository usuarioRepository;

  /**
   * Inicia sesión si las credenciales son válidas.
   *
   * @return sesión activa, o null si las credenciales son inválidas
   */
  public Sesion iniciarSesion(String usuario, String password) {
    if (validarCredenciales(usuario, password)) {
      return new Sesion(buscarUsuario(usuario));
    }
    return null;
  }

  /**
   * Valida las credenciales manejando los intentos fallidos.
   */
  public boolean validarCredenciales(String usuario, String password) {
    boolean valido = sistemaAutenticacion.validarCredenciales(usuario, password);
    if (!valido) {
      manejarIntentosFallidos(usuario);
    }
    return valido;
  }

  /**
   * CU-01: máximo 3 intentos, luego se bloquea temporalmente la cuenta.
   */
  public void manejarIntentosFallidos(String usuario) {
    Usuario u = buscarUsuario(usuario);
    if (u != null) {
      u.bloquear();
    }
  }

  /**
   * @return rol del usuario, o null si no existe
   */
  public Rol obtenerRol(String usuario) {
    return sistemaAutenticacion.obtenerRol(usuario);
  }

  private Usuario buscarUsuario(String usuario) {
    return usuarioRepository.findByEmail(usuario).orElse(null);
  }
}
