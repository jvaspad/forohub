package com.alura.forohub.domain.curso.validations;

import com.alura.forohub.domain.curso.CursoRepository;
import com.alura.forohub.domain.topico.DatosActualizarTopico;
import com.alura.forohub.domain.topico.validations.ValidadorTopicoActualizado;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCursoActualizado implements ValidadorTopicoActualizado{

    @Autowired
    private CursoRepository repository;

    @Override
    public void validate(DatosActualizarTopico data) {
        if(data.cursoId() != null){
            var ExisteCurso = repository.existsById(data.cursoId());
            if (!ExisteCurso){
                throw new ValidationException("Este curso no existe");
            }

            var cursoHabilitado = repository.findById(data.cursoId()).get().getActivo();
            if(!cursoHabilitado){
                throw new ValidationException("Este curso no esta disponible en este momento.");
            }
        }

    }
}
