package com.javanata.agendador_tarefas.infrastructure.security;


import com.javanata.agendador_tarefas.business.dto.UsuarioDTO;
import com.javanata.agendador_tarefas.infrastructure.security.client.UsuarioClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    private UsuarioClient usuarioClient;


    // Implementação do método para carregar detalhes do usuário pelo e-mail e token
    public UserDetails carregaDadosUsuario(String email, String token)
            throws UsernameNotFoundException {

        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioPorEmail(email,token);

        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}
