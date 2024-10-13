package com.taqui.api_mvc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class  UsuarioRequest{

        private Long id;

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 50, message = "O campo pode ter no máximo 50 caracteres")
        private String nome;

        @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).*$", message = "A senha deve conter pelo menos um caractere especial e uma letra maiúscula")
        @Size(max = 15, min = 12, message = "A senha deve ter no minimo 12 e maximo de 15 caracteres")
        private String senha;


        @NotBlank(message = "O campo não pode estar vazio")
        @Email
        @Size(max = 50, message = "O campo pode ter no máximo 50 caracteres")
        private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public  String getSenha() {
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}