package com.alura.forohub.domain.curso;

public record DatosActualizarCurso(
        String name,
        Categoria categoria,
        Boolean activo
) {
}
