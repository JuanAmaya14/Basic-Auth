package com.Amaya.Basic.Auth.Repositortios;

import com.Amaya.Basic.Auth.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
}
