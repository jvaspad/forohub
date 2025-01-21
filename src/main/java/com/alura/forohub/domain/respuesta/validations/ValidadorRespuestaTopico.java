package com.alura.forohub.domain.respuesta.validations;

import com.alura.forohub.domain.respuesta.DatosCrearRespuesta;
import com.alura.forohub.domain.topico.Estado;
import com.alura.forohub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRespuestaTopico implements ValidadorRespuestaCreada{

    @Autowired
    private TopicoRepository repository;

    @Override
    public void validate(DatosCrearRespuesta data) {
        var topicoExiste = repository.existsById(data.topicoId());

        if (!topicoExiste){
            throw new ValidationException("Este tópico no existe.");
        }

        var topicoAbierto = repository.findById(data.topicoId()).get().getEstado();

        if(topicoAbierto != Estado.OPEN){
            throw new ValidationException("Este tópico no está abierto.");
        }

    }
}
