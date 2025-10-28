package com.javanauta.agendadortarefas.infrastructure.security.client;


import com.javanauta.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "usuario",url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam("email")String email,
                                     @RequestHeader("Authorization") String token);
}
