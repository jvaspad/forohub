package com.alura.forohub.domain.usuario;

public record DatosActualizarUsuario (
        String contrasena,
        Rol rol,
        String nombre,
        String apellido,
        String email,
        Boolean enabled
) {
}
