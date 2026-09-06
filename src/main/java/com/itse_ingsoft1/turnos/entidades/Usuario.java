package com.itse_ingsoft1.turnos.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Usuario del sistema (paciente o secretaría).
 */
@Entity
@Table(name = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

  public static final int MAX_INTENTOS_FALLIDOS = 3;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idUsuario;

  @Column(nullable = false)
  private String nombre;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String passwordHash;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Rol rol;

  @Column(nullable = false)
  @Builder.Default
  private int intentosFallidos = 0;

  @Column(nullable = false)
  @Builder.Default
  private boolean bloqueado = false;

  private LocalDateTime bloqueadoHasta;

  /**
   * CU-01: valida las credenciales del usuario.
   *
   * @param usuario  nombre de usuario (email)
   * @param password contraseña en texto plano
   * @return true si las credenciales son válidas
   */
  public boolean autenticar(String usuario, String password) {
    if (bloqueado || (bloqueadoHasta != null && bloqueadoHasta.isAfter(LocalDateTime.now()))) {
      return false;
    }
    boolean valido = this.email.equals(usuario)
        && this.passwordHash.equals(password);
    if (!valido) {
      manejarIntentoFallido();
    } else {
      intentosFallidos = 0;
    }
    return valido;
  }

  public Rol obtenerRol() {
    return rol;
  }

  /**
   * CU-01: bloquea temporalmente la cuenta tras los intentos fallidos permitidos.
   */
  public void bloquear() {
    this.bloqueado = true;
  }

  public void desbloquear() {
    this.bloqueado = false;
    this.bloqueadoHasta = null;
    this.intentosFallidos = 0;
  }

  private void manejarIntentoFallido() {
    intentosFallidos++;
    if (intentosFallidos >= MAX_INTENTOS_FALLIDOS) {
      bloquear();
    }
  }

  public boolean estaBloqueado() {
    return bloqueado || (bloqueadoHasta != null && bloqueadoHasta.isAfter(LocalDateTime.now()));
  }
}
