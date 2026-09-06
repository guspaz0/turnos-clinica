package com.itse_ingsoft1.turnos.servicios;

import com.itse_ingsoft1.turnos.dominio.SistemaAutenticacion;
import com.itse_ingsoft1.turnos.entidades.Rol;
import com.itse_ingsoft1.turnos.entidades.Usuario;
import com.itse_ingsoft1.turnos.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementación del sistema de autenticación (CU-01).
 */
@Service
@RequiredArgsConstructor
public class ImplementacionSistemaAutenticacion implements SistemaAutenticacion {

  private final UsuarioRepository usuarioRepository;

  @Override
  public boolean validarCredenciales(String usuario, String password) {
    return usuarioRepository.findByEmail(usuario)
        .map(u -> u.autenticar(usuario, password))
        .orElse(false);
  }

  @Override
  public Rol obtenerRol(String usuario) {
    return usuarioRepository.findByEmail(usuario)
        .map(Usuario::obtenerRol)
        .orElse(null);
  }
}
