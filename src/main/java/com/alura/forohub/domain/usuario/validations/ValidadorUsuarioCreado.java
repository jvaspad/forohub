package com.alura.forohub.domain.usuario.validations;

import com.alura.forohub.domain.usuario.DatosCrearUsuario;

public interface ValidadorUsuarioCreado {
    void validate(DatosCrearUsuario data);
}