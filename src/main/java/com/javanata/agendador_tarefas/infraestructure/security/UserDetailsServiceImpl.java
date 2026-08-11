package com.javanata.agendador_tarefas.infraestructure.security;


import com.javanata.agendador_tarefas.infraestructure.client.UsuarioClient;
import com.javanata.agendador_tarefas.infraestructure.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;

    public UserDetails carregaDadosUsuario(String email, String token){
        UsuarioDTO usuarioDTO = client.busscarUsuarioPorEmail(email,token);
        return User
                .withUsername(usuarioDTO .getEmail())
                .password(usuarioDTO .getSenha())
                .build();
    }

}
