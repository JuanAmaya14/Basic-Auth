package com.Amaya.Basic.Auth.Domain.Datos;

import jakarta.validation.constraints.NotNull;

public record DatosModificarUsuario (

        @NotNull
        long id,

        String nombre,

        String clave
){
}
