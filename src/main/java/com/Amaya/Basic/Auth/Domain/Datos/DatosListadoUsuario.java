package com.Amaya.Basic.Auth.Domain.Datos;

import com.Amaya.Basic.Auth.Domain.Usuario;

public record DatosListadoUsuario(

        long id,
        String nombre,
        String clave

) {

    public DatosListadoUsuario (Usuario usuario){

        this(usuario.getId(), usuario.getNombre(), usuario.getClave());

    }
}
