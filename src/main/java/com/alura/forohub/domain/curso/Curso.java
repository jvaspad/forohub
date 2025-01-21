package com.alura.forohub.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cursos")
@Entity(name = "Curso")
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Boolean activo;

    public Curso(DatosCrearCurso datosCrearCurso) {
        this.name = datosCrearCurso.name();
        this.categoria = datosCrearCurso.categoria();
        this.activo = true;
    }

    public void actualizarCurso(DatosActualizarCurso datosActualizarCurso) {

        if(datosActualizarCurso.name() != null){
            this.name = datosActualizarCurso.name();
        }
        if (datosActualizarCurso.categoria() != null){
            this.categoria = datosActualizarCurso.categoria();
        }
        if (datosActualizarCurso.activo() != null){
            this.activo = datosActualizarCurso.activo();
        }
    }

    public void eliminarCurso(){
        this.activo = false;
    }
}