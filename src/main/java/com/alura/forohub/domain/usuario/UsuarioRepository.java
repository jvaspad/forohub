package com.alura.forohub.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByNombreDeUsuario(String nombreDeUsuario);

    UserDetails findByEmail(String email);

    Page<Usuario> findAllByEnabledTrue(Pageable pageable);

    @SuppressWarnings("null")
    Usuario getReferenceById(Long id);

    @SuppressWarnings("null")
    Page<Usuario> findAll(Pageable pageable);

    Usuario getReferenceByNombreDeUsuario(String nombreDeUsuario);

    Boolean existsByNombreDeUsuario(String nombreDeUsuario);
}
