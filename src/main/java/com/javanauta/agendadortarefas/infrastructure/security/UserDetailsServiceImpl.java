package com.javanauta.agendadortarefas.infrastructure.security;




import com.javanauta.agendadortarefas.business.dto.UsuarioDTO;
import com.javanauta.agendadortarefas.infrastructure.security.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  {


    @Autowired
    private UsuarioClient usuarioClient;

    // Implementação do método para carregar detalhes do usuário pelo e-mail e token
    public UserDetails carregaDadosUsuario(String email, String token){

        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioPorEmail(email,token);
        return User
                .withUsername(usuarioDTO.getEmail())//Define nome do usuario
                .password(usuarioDTO.getSenha())//Define senha
                .build();

    }
}
