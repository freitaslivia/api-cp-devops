package com.taqui.api_mvc.service;

import com.taqui.api_mvc.dto.UsuarioRequest;
import com.taqui.api_mvc.dto.UsuarioResponse;
import com.taqui.api_mvc.model.Usuario;
import com.taqui.api_mvc.repository.ProdutoRepository;
import com.taqui.api_mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Usuario requestToUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setEmail(usuarioRequest.getEmail());

        return usuario;
    }

    public UsuarioResponse usuarioToResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    public UsuarioRequest usuarioToRequest(Usuario usuario) {
        UsuarioRequest usuarioRequest = new UsuarioRequest();

        usuarioRequest.setNome(usuario.getNome());
        usuarioRequest.setSenha(usuario.getSenha());
        usuarioRequest.setEmail(usuario.getEmail());

        return usuarioRequest;
    }

    @Transactional
    public UsuarioResponse criarNovoUsuario(UsuarioRequest novoUsuario) {

        if (usuarioRepository.existsByEmail(novoUsuario.getEmail())) {
            throw new ErroNegocioException("Já existe um usuário com o mesmo EMAIL");
        }
        Usuario usuario = requestToUsuario(novoUsuario);

        usuario = this.usuarioRepository.save(usuario);

        return new UsuarioResponse (usuario);

    }

    @Transactional
    public void deletarUsuarioEProdutos(Long id) {

        produtoRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);
    }

}