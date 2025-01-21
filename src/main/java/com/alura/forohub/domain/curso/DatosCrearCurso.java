package com.alura.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearCurso(
        @NotBlank String name,
        @NotNull Categoria categoria) {
}
