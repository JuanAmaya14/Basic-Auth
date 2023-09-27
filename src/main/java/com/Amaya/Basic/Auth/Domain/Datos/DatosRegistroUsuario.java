package com.Amaya.Basic.Auth.Domain.Datos;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario (

        @NotBlank
        String nombre,

        @NotBlank
        String clave) {
}
