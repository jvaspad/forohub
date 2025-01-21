package com.alura.forohub.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuarios")
@Entity(name="Usuario")
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails{

    @SuppressWarnings("unused")
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreDeUsuario;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    private String nombre;
    private String apellido;
    private String email;
    private Boolean enabled;

    public Usuario(DatosCrearUsuario datosCrearUsuario, String hashedPassword) {
        this.nombreDeUsuario = datosCrearUsuario.nombreDeUsuario();
        this.contrasena = hashedPassword;
        this.rol = Rol.USUARIO;
        this.nombre = capitalizado(datosCrearUsuario.nombre());
        this.apellido = capitalizado(datosCrearUsuario.apellido());
        this.email = datosCrearUsuario.email();
        this.enabled = true;
    }

    public void actualizarUsuarioConContrasena(DatosActualizarUsuario datosActualizarUsuario, String hashedPassword) {
        if (datosActualizarUsuario.contrasena() != null){
            this.contrasena = hashedPassword;
        }
        if (datosActualizarUsuario.rol() != null){
            this.rol = datosActualizarUsuario.rol();
        }
        if (datosActualizarUsuario.nombre() != null){
            this.nombre = capitalizado(datosActualizarUsuario.nombre());
        }
        if (datosActualizarUsuario.apellido() != null){
            this.apellido = capitalizado(datosActualizarUsuario.apellido());
        }
        if (datosActualizarUsuario.email() != null){
            this.email = datosActualizarUsuario.email();
        }
        if (datosActualizarUsuario.enabled() != null){
            this.enabled = datosActualizarUsuario.enabled();
        }
    }

    public void actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario) {
        if (datosActualizarUsuario.rol() != null){
            this.rol = datosActualizarUsuario.rol();
        }
        if (datosActualizarUsuario.nombre() != null){
            this.nombre = capitalizado(datosActualizarUsuario.nombre());
        }
        if (datosActualizarUsuario.apellido() != null){
            this.apellido = capitalizado(datosActualizarUsuario.apellido());
        }
        if (datosActualizarUsuario.email() != null){
            this.email = datosActualizarUsuario.email();
        }
        if (datosActualizarUsuario.enabled() != null){
            this.enabled = datosActualizarUsuario.enabled();
        }
    }

    public void eliminarUsuario(){
        this.enabled = false;
    }

    private String capitalizado(String string) {
        return string.substring(0,1).toUpperCase()+string.substring(1).toLowerCase();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return contrasena;
    }

    public String getUsername() {
        return nombreDeUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}