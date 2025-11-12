package com.javanata.agendador_tarefas.infrastructure.security.client;


import com.javanata.agendador_tarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario",url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTO bucarUsuarioPorEmail(@RequestParam("email")String email,
                                    @RequestHeader("Autorization")String token);




}
