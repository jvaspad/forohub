package com.alura.forohub.domain.respuesta.validations;

import com.alura.forohub.domain.respuesta.DatosActualizarRespuesta;

public interface ValidadorRespuestaActualizada {
    void validate(DatosActualizarRespuesta data, Long respuestaId);
}
