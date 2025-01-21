package com.alura.forohub.domain.respuesta.validations;

import com.alura.forohub.domain.respuesta.DatosCrearRespuesta;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRespuestaUsuario implements ValidadorRespuestaCreada{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(DatosCrearRespuesta data) {
        var usuarioExiste = repository.existsById(data.usuarioId());

        if(!usuarioExiste){
            throw new ValidationException("Este usuario no existe.");
        }

        var usuarioHabilitado = repository.findById(data.usuarioId()).get().isEnabled();

        if(!usuarioHabilitado){
            throw new ValidationException("Este usuario no esta habilitado.");
        }
    }
}
