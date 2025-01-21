package com.alura.forohub.domain.respuesta;

public record DatosActualizarRespuesta (
        String mensaje,
        Boolean solucion,
        Boolean borrado
) {
}
