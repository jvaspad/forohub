package com.alura.forohub.domain.usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombreDeUsuario,
        Rol rol,
        String nombre,
        String apellido,
        String email,
        Boolean enabled
) {

    public DatosDetalleUsuario(Usuario usuario){
        this(usuario.getId(),
                usuario.getNombreDeUsuario(),
                usuario.getRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getEnabled()
        );
    }
}
