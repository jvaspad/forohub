package com.alura.forohub.domain.topico.validations;

import com.alura.forohub.domain.topico.DatosCrearTopico;
import com.alura.forohub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoDuplicado implements ValidadorTopicoCreado{

    @Autowired
    private TopicoRepository topicoRepository;


    @Override
    public void validate(DatosCrearTopico data) {
        var topicoDuplicado = topicoRepository.existsByTituloAndMensaje(data.titulo(), data.mensaje());
        if(topicoDuplicado){
            throw new ValidationException("Este topico ya existe. Revisa /topicos/" + topicoRepository.findByTitulo(data.titulo()).getId());

        }
    }
}