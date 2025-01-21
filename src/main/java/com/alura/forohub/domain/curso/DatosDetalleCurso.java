package com.alura.forohub.domain.curso;

public record DatosDetalleCurso(
        Long id,
        String name,
        Categoria categoria,
        Boolean activo) {

    public DatosDetalleCurso(Curso curso){
        this(
                curso.getId(),
                curso.getName(),
                curso.getCategoria(),
                curso.getActivo());
    }

}