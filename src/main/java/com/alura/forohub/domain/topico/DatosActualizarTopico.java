package com.alura.forohub.domain.topico;

public record DatosActualizarTopico (
        String titulo,
        String mensaje,
        Estado estado,
        Long cursoId
){
}
