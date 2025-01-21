package com.alura.forohub.domain.topico.validations;

import com.alura.forohub.domain.topico.DatosCrearTopico;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoUsuario implements ValidadorTopicoCreado{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(DatosCrearTopico data) {
        var existeUsuario = repository.existsById(data.usuarioId());
        if (!existeUsuario) {
            throw new ValidationException("Este usuario no existe.");
        }

        var usuarioHabilitado = repository.findById(data.usuarioId()).get().getEnabled();
        if (!usuarioHabilitado) {
            throw new ValidationException("Este usuario fue deshabiliado.");
        }
    }
}
