package com.Amaya.Basic.Auth.Config;

import com.Amaya.Basic.Auth.Domain.Usuario;
import com.Amaya.Basic.Auth.Repositortios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombre(username);

        if (usuario != null) {
            return new User(usuario.getNombre(), usuario.getClave(), usuario.getAuthorities());
        } else {
            throw new UsernameNotFoundException("El usuario: " + usuario + " no existe");
        }

    }
}
