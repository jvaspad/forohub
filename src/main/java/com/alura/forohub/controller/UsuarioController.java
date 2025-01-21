package com.alura.forohub.controller;

import com.alura.forohub.domain.usuario.*;
import com.alura.forohub.domain.usuario.validations.ValidadorUsuarioActualizado;
import com.alura.forohub.domain.usuario.validations.ValidadorUsuarioCreado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    List<ValidadorUsuarioCreado> crearValidador;

    @Autowired
    List<ValidadorUsuarioActualizado> actualizarValidador;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleUsuario> crearUsuario(@RequestBody @Valid DatosCrearUsuario datosCrearUsuario,
                                                            UriComponentsBuilder uriBuilder){
        crearValidador.forEach(v -> v.validate(datosCrearUsuario));

        String hashedPassword = passwordEncoder.encode(datosCrearUsuario.contrasena());
        Usuario usuario = new Usuario(datosCrearUsuario, hashedPassword);

        repository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{username}").buildAndExpand(usuario.getNombreDeUsuario()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DatosDetalleUsuario>> leerTodosUsuarios(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        var pagina = repository.findAll(pageable).map(DatosDetalleUsuario::new);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleUsuario>> leerUsuariosActivos(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        var pagina = repository.findAllByEnabledTrue(pageable).map(DatosDetalleUsuario::new);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<DatosDetalleUsuario> leerUnUsuario(@PathVariable String nombreDeUsuario){
        Usuario usuario = (Usuario) repository.findByNombreDeUsuario(nombreDeUsuario);
        var datosUsuario = new DatosDetalleUsuario(
                usuario.getId(),
                usuario.getNombreDeUsuario(),
                usuario.getRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getEnabled()
        );
        return ResponseEntity.ok(datosUsuario);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Lee un único usuario por su ID")
    public ResponseEntity<DatosDetalleUsuario>leerUnUsuario(@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        var datosUsuario = new DatosDetalleUsuario(
                usuario.getId(),
                usuario.getNombreDeUsuario(),
                usuario.getRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getEnabled()
        );
        return ResponseEntity.ok(datosUsuario);
    }

    @PutMapping("/{username}")
    @Transactional
    public ResponseEntity<DatosDetalleUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario, @PathVariable String nombreDeUsuario){
        actualizarValidador.forEach(v -> v.validate(datosActualizarUsuario));

        Usuario usuario = (Usuario) repository.findByNombreDeUsuario(nombreDeUsuario);

        if (datosActualizarUsuario.contrasena() != null){
            String hashedPassword = passwordEncoder.encode(datosActualizarUsuario.contrasena());
            usuario.actualizarUsuarioConContrasena(datosActualizarUsuario, hashedPassword);

        }else {
            usuario.actualizarUsuario(datosActualizarUsuario);
        }

        var datosUsuario = new DatosDetalleUsuario(
                usuario.getId(),
                usuario.getNombreDeUsuario(),
                usuario.getRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getEnabled()
        );
        return ResponseEntity.ok(datosUsuario);
    }

    @DeleteMapping("/{username}")
    @Transactional
    public ResponseEntity<?> eliminarUsuario(@PathVariable String nombreDeUsuario){
        Usuario usuario = (Usuario) repository.findByNombreDeUsuario(nombreDeUsuario);
        usuario.eliminarUsuario();
        return ResponseEntity.noContent().build();
    }
}
