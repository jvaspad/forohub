package com.alura.forohub.domain.usuario.validations;

import com.alura.forohub.domain.usuario.DatosCrearUsuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioDuplicado implements ValidadorUsuarioCreado{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(DatosCrearUsuario data) {
        var usuarioDuplicado = repository.findByNombreDeUsuario(data.nombreDeUsuario());
        if(usuarioDuplicado != null){
            throw new ValidationException("Este usuario ya existe.");
        }

        var emailDuplicado = repository.findByEmail(data.email());
        if(emailDuplicado != null){
            throw new ValidationException("Este email ya está en uso.");
        }
    }
}