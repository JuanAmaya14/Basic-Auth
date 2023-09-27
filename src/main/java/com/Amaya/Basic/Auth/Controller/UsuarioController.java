package com.Amaya.Basic.Auth.Controller;

import com.Amaya.Basic.Auth.Domain.Datos.DatosListadoUsuario;
import com.Amaya.Basic.Auth.Domain.Datos.DatosModificarUsuario;
import com.Amaya.Basic.Auth.Domain.Datos.DatosRespuestaUsuario;
import com.Amaya.Basic.Auth.Domain.Usuario;
import com.Amaya.Basic.Auth.Repositortios.UsuarioRepository;
import com.Amaya.Basic.Auth.Domain.Datos.DatosRegistroUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity RegistrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                           UriComponentsBuilder uriComponentsBuilder) {

        String clave = passwordEncoder.encode(datosRegistroUsuario.clave());

        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario, clave));

        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getClave());

        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(datosRespuestaUsuario);

    }

    @GetMapping
    public ResponseEntity ListarUsuarios() {

        return ResponseEntity.ok(usuarioRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoUsuario> ListarUsuarioPorId(@PathVariable long id) {

        Usuario usuario = usuarioRepository.getReferenceById(id);

        DatosListadoUsuario datosListadoUsuario = new DatosListadoUsuario(usuario.getNombre(),
                usuario.getClave());

        return ResponseEntity.ok(datosListadoUsuario);

    }

    @PutMapping("/modificar")
    @Transactional
    public ResponseEntity modificarUsuario(@RequestBody @Valid DatosModificarUsuario datosModificarUsuario) {

        String contrasenha = passwordEncoder.encode(datosModificarUsuario.clave());

        Usuario usuario = usuarioRepository.getReferenceById(datosModificarUsuario.id());

        usuario.modificarUsuario(datosModificarUsuario, contrasenha);

        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getClave());

        return ResponseEntity.ok(datosRespuestaUsuario);

    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable long id) {

        usuarioRepository.deleteById(id);

        return ResponseEntity.ok("El usuario con el id: " + id + " fue eliminado exitosamente");

    }

}
