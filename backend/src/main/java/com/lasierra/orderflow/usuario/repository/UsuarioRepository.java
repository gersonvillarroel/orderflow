package com.lasierra.orderflow.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lasierra.orderflow.usuario.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Short>{

    /*
     * Busca un usuario por su nombre de usuario y carga sus roles y privilegios.
     */
    @EntityGraph(attributePaths = {"rol", "rol.privilegios"})
    Optional<Usuario> findByUsername(String username);
}
