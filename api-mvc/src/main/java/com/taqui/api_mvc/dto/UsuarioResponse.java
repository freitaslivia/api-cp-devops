package com.taqui.api_mvc.dto;

import com.taqui.api_mvc.model.Usuario;

public record UsuarioResponse(

        Long id,

        String nome,

        String email
) {

    public UsuarioResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}