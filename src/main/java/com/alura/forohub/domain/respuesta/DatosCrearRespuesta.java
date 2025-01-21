package com.alura.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearRespuesta (
        @NotBlank String mensaje,
        @NotNull Long usuarioId,
        @NotNull long topicoId
) {
}
