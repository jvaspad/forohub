package com.alura.forohub.domain.usuario.validations;

import com.alura.forohub.domain.usuario.DatosActualizarUsuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioEmailEnUso implements ValidadorUsuarioActualizado {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(DatosActualizarUsuario data) {
        if(data.email() != null){
            var emailDuplicado = repository.findByEmail(data.email());
            if(emailDuplicado != null){
                throw new ValidationException("Este email ya esta en uso.");
            }
        }
    }
}